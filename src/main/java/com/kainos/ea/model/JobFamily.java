package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobFamily {
    private int jobFamilyId;
    private int capabilityId;
    private String name;

    public int getJobFamilyId() {
        return jobFamilyId;
    }

    public void setJobFamilyId(int jobFamilyId) {
        this.jobFamilyId = jobFamilyId;
    }

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
    public JobFamily(
            @JsonProperty("job_family_id") int jobFamilyId,
            @JsonProperty("capability_id") int capabilityId,
            @JsonProperty("name") String name) {
        setJobFamilyId(jobFamilyId);
        setCapabilityId(capabilityId);
        setName(name);
    }
}
