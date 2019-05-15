package com.usb.compute.quartz.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usb.compute.controller.CuratedMachineController;
import com.usb.compute.model.ComputeRequest;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ComputeRequestProcessor extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(ComputeRequestProcessor.class);

    @Autowired
    private ObjectMapper json;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("executing with {}", jobExecutionContext.getJobDetail().getKey());

        try {
            JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
            String strRequest
                    = jobDataMap.getString(CuratedMachineController.JOB_KEY_COMPUTE_REQUEST);

            ComputeRequest request = json.readValue(strRequest, ComputeRequest.class);
        } catch (IOException e) {
            throw new JobExecutionException();
        }


    }
}
