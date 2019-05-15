package com.usb.compute.service;

import com.usb.compute.model.CuratedMachine;
import com.usb.compute.repository.CuratedMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

import java.util.List;


@Service
public class CuratedMachineService {

    @Autowired
    private CuratedMachineRepository curatedMachineRepository;

    @Autowired
    private EntityManager entityManager;

    public List<CuratedMachine> findMachines(Integer ramCount,
                                             Integer cpuCount,
                                             Integer gpuCount,
                                             String gpuType) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CuratedMachine> cq = cb.createQuery(CuratedMachine.class);
        Root<CuratedMachine> root = cq.from(CuratedMachine.class);
        cq.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (ramCount != null) {
            predicates.add(cb.equal(root.get("ramCount"), ramCount));
        }
        if (cpuCount != null) {
            predicates.add(cb.equal(root.get("cpuCount"), cpuCount));
        }
        if (gpuCount != null) {
            predicates.add(cb.equal(root.get("gpuCount"), gpuCount));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }

}

