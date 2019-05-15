package com.usb.compute.model;


import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class RequestUser {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    public RequestUser() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
