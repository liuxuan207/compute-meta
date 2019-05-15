package com.usb.compute.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class CloudProvider {

    @Id
    @Enumerated(EnumType.STRING)
    private CloudProviderType id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "provider")
    private List<CuratedSoftware> curatedSoftwares;

    public CloudProviderType getId() {
        return id;
    }

    public void setId(CloudProviderType id) {
        this.id = id;
    }

    public List<CuratedSoftware> getCuratedSoftwares() {
        return curatedSoftwares;
    }

    public void setCuratedSoftwares(List<CuratedSoftware> curatedSoftwares) {
        this.curatedSoftwares = curatedSoftwares;
    }
}
