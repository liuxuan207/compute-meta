package com.usb.compute.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class CuratedMachine {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private CloudProvider cloudProvider;

    private Integer cpuCount;

    private Integer ramCount;

    @Enumerated(EnumType.STRING)
    private GPUType gpuType;

    private Integer gpuCount;

    private String displayName;

    public CuratedMachine() {
    }

    public CuratedMachine(CloudProvider cloudProvider,
                          Integer cpuCount,
                          Integer ramCount,
                          GPUType gpuType,
                          Integer gpuCount,
                          String displayName) {
        this.cloudProvider = cloudProvider;
        this.cpuCount = cpuCount;
        this.ramCount = ramCount;
        this.gpuType = gpuType;
        this.gpuCount = gpuCount;
        this.displayName = displayName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CloudProvider getCloudProvider() {
        return cloudProvider;
    }

    public void setCloudProvider(CloudProvider cloudProvider) {
        this.cloudProvider = cloudProvider;
    }

    public Integer getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(Integer cpuCount) {
        this.cpuCount = cpuCount;
    }

    public Integer getRamCount() {
        return ramCount;
    }

    public void setRamCount(Integer ramCount) {
        this.ramCount = ramCount;
    }

    public GPUType getGpuType() {
        return gpuType;
    }

    public void setGpuType(GPUType gpuType) {
        this.gpuType = gpuType;
    }

    public Integer getGpuCount() {
        return gpuCount;
    }

    public void setGpuCount(Integer gpuCount) {
        this.gpuCount = gpuCount;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}
