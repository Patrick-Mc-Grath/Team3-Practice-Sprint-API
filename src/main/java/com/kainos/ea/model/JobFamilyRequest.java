package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobFamilyRequest {

    private int capabilityId;
    private String name;

    public int getCapabilityId() {
        return capabilityId;
    }
    public void setCapabilityId(int capabilityId) {
        this.capabilityId = capabilityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonCreator
    public JobFamilyRequest(
            @JsonProperty ("capability_id") int capabilityId,
            @JsonProperty ("name") String name)
    {
        setCapabilityId(capabilityId);
        setName(name);
    }
}
