package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobFamily {

    private int jobFamilyId;
    private String capabilityName;
    private String name;

    public int getJobFamilyId() {
        return jobFamilyId;
    }

    public void setJobFamilyId(int jobFamilyId) {
        this.jobFamilyId = jobFamilyId;
    }

    public String getCapabilityName() {
        return capabilityName;
    }

    public void setCapabilityName(String capabilityName) {
        this.capabilityName = capabilityName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonCreator
    public JobFamily(
                    @JsonProperty ("job_family_id") int jobFamilyId,
                    @JsonProperty ("Capabilities.name") String capabilityName,
                    @JsonProperty ("name") String name)
    {
        setJobFamilyId(jobFamilyId);
        setCapabilityName(capabilityName);
        setName(name);
    }
}
