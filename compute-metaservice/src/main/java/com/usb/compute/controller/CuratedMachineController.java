package com.usb.compute.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usb.compute.model.ComputeRequest;
import com.usb.compute.model.CuratedMachine;
import com.usb.compute.quartz.processor.ComputeRequestProcessor;
import com.usb.compute.service.ComputeRequestService;
import com.usb.compute.service.CuratedMachineService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CuratedMachineController {

    public static final String JOB_KEY_COMPUTE_REQUEST = "computeRequest";

    @Autowired
    private CuratedMachineService curatedMachineService;

    @Autowired
    private ComputeRequestService computeRequestService;

    @Autowired
    private ObjectMapper json;

    @Autowired
    private Scheduler scheduler;

    @RequestMapping(method = RequestMethod.GET, value = "/catalog")
    public List<CuratedMachine> findFromCatalog(@RequestParam(required = false) String cpuCount,
                                            @RequestParam(required = false) String ramCount,
                                            @RequestParam(required = false) String gpuCount,
                                            @RequestParam(required = false) String cloudProvider,
                                            @RequestParam(required = false) String gpuType) {

        List<CuratedMachine> machines = curatedMachineService
                .findMachines(
                        ramCount != null ? Integer.valueOf(ramCount) : null,
                        cpuCount != null ? Integer.valueOf(cpuCount) : null,
                        gpuCount != null ? Integer.valueOf(gpuCount) : null,
                        gpuType);


        return machines;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/request/create")
    public ComputeRequest submitComputeRequest(@RequestBody ComputeRequest request)
            throws JsonProcessingException, SchedulerException {

        ComputeRequest computeRequest = computeRequestService.createComputeRequest(request);

        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put(JOB_KEY_COMPUTE_REQUEST, json.writeValueAsString(computeRequest));

        JobDetail jobDetail = JobBuilder.newJob(ComputeRequestProcessor.class)
                .withIdentity(UUID.randomUUID().toString(), "compute-request-init")
                .withDescription("Start processing request id = " + computeRequest.getId())
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();


        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "email-triggers")
                .withDescription("request process trigger")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();

        scheduler.scheduleJob(jobDetail, trigger);

        return computeRequest;
    }
}
