package com.usb.compute.repository;

import com.usb.compute.model.CloudProvider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CloudProviderRepository extends CrudRepository<CloudProvider, String> {
}
