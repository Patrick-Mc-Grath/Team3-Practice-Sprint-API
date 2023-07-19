package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Capabilities {
    private int CapabilityId;
    private String Name;
    private String Description;
    private int JobRoleId;

    public int getCapabilityId() {
        return CapabilityId;
    }

    public void setCapabilityId(int capabilityId) {
        CapabilityId = capabilityId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getJobRoleId() {
        return JobRoleId;
    }

    public void setJobRoleId(int jobRoleId) {
        JobRoleId = jobRoleId;
    }

    @JsonCreator
    public Capabilities(@JsonProperty("CapabilityId") int CapabilityId,
                        @JsonProperty("Name") String Name,
                        @JsonProperty("Description") String Description,
                        @JsonProperty("JobRoleId") int JobRoleId)
    {
        setCapabilityId(CapabilityId);
        setName(Name);
        setDescription(Description);
        setJobRoleId(JobRoleId);

    }
}
