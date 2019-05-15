package com.usb.compute.model;


import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class ComputeRequest {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private CuratedMachine curatedMachine;

    @ManyToMany
    private List<CuratedSoftware> curatedSoftwares;

    private Integer numberOfMachines;

    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.CREATED;

    @Basic
    private ZonedDateTime startDate;
    @Basic
    private ZonedDateTime endDate;

    @Basic
    private ZonedDateTime actualStartDate;
    @Basic
    private ZonedDateTime actualEndDate;

    private String assignedMachineId;
    private String referenceNumber;
    private String chargeForCostCenter;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private RequestUser user;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public RequestUser getUser() {
        return user;
    }

    public void setUser(RequestUser user) {
        this.user = user;
    }

    public CuratedMachine getCuratedMachine() {
        return curatedMachine;
    }

    public void setCuratedMachine(CuratedMachine curatedMachine) {
        this.curatedMachine = curatedMachine;
    }

    public List<CuratedSoftware> getCuratedSoftwares() {
        return curatedSoftwares;
    }

    public void setCuratedSoftwares(List<CuratedSoftware> curatedSoftwares) {
        this.curatedSoftwares = curatedSoftwares;
    }

    public Integer getNumberOfMachines() {
        return numberOfMachines;
    }

    public void setNumberOfMachines(Integer numberOfMachines) {
        this.numberOfMachines = numberOfMachines;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public ZonedDateTime getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(ZonedDateTime actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public ZonedDateTime getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(ZonedDateTime actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public String getAssignedMachineId() {
        return assignedMachineId;
    }

    public void setAssignedMachineId(String assignedMachineId) {
        this.assignedMachineId = assignedMachineId;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getChargeForCostCenter() {
        return chargeForCostCenter;
    }

    public void setChargeForCostCenter(String chargeForCostCenter) {
        this.chargeForCostCenter = chargeForCostCenter;
    }
}
