package com.usb.compute.repository;

import com.usb.compute.model.CuratedMachine;
import com.usb.compute.model.CuratedSoftware;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CuratedSoftwareRepository extends CrudRepository<CuratedSoftware, UUID> {
}