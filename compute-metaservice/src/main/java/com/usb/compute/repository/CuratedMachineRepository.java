package com.usb.compute.repository;

import com.usb.compute.model.CuratedMachine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CuratedMachineRepository extends CrudRepository<CuratedMachine, UUID> {
}