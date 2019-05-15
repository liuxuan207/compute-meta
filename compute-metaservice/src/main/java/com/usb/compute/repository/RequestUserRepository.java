package com.usb.compute.repository;

import com.usb.compute.model.RequestUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;

@Repository
public interface RequestUserRepository extends CrudRepository<RequestUser, UUID> {


}

