package com.usb.compute;

import com.usb.compute.model.*;
import com.usb.compute.repository.CloudProviderRepository;
import com.usb.compute.repository.CuratedMachineRepository;
import com.usb.compute.repository.RequestUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class DataLoader implements ApplicationListener<ApplicationReadyEvent> {


    @Autowired
    private CuratedMachineRepository curatedMachineRepository;
    @Autowired
    private CloudProviderRepository cloudProviderRepository;
    @Autowired
    private RequestUserRepository requestUserRepository;

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        createCuratedMachine();
        createCuratedMachine2();
        createUser();
    }

    private void createUser() {
        try {
            if(requestUserRepository.count() < 1) {
                RequestUser u = new RequestUser();
                u.setId(UUID.fromString("9e0b96f2-d1e5-42e0-bb1e-3c90d1abd1f9"));
                requestUserRepository.save(u);
            }
        } catch (Exception e) { /*  */ }
    }

    private void createCuratedMachine2() {
        try {
            CloudProvider aws = new CloudProvider();
            aws.setId(CloudProviderType.GCP);
            //cloudProviderRepository.save(aws);

            CuratedSoftware s1 = new CuratedSoftware("name3", "uri://3", aws);
            CuratedSoftware s2 = new CuratedSoftware("name4", "uri://4", aws);

            ArrayList<CuratedSoftware> list = new ArrayList<>();
            list.add(s1);
            list.add(s2);

            aws.setCuratedSoftwares(list);
            cloudProviderRepository.save(aws);

            curatedMachineRepository.save(
                    new CuratedMachine(aws,
                            4,
                            16,
                            GPUType.NVIDIA,
                            2,
                            "Machine 2"));
        } catch (Throwable t) { /* might fail uniqueness constraints */ }
    }

    private void createCuratedMachine() {
        try {
            CloudProvider aws = new CloudProvider();
            aws.setId(CloudProviderType.AWS);
            //cloudProviderRepository.save(aws);

            CuratedSoftware s1 = new CuratedSoftware("name1", "uri://1", aws);
            CuratedSoftware s2 = new CuratedSoftware("name2", "uri://2", aws);

            ArrayList<CuratedSoftware> list = new ArrayList<>();
            list.add(s1);
            list.add(s2);

            aws.setCuratedSoftwares(list);
            cloudProviderRepository.save(aws);

            curatedMachineRepository.save(
                    new CuratedMachine(aws,
                            1,
                            1,
                            GPUType.NVIDIA,
                            1,
                            "Machine 1"));
        } catch (Throwable t) { /* might fail uniqueness constraints */ }
    }

}