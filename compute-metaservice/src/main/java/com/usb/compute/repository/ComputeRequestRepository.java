package com.usb.compute.repository;

import com.usb.compute.model.ComputeRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ComputeRequestRepository extends CrudRepository<ComputeRequest, UUID> {

}

