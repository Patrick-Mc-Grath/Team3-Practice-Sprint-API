package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Capabilities {
    private int capability_id;
    private String name;
    private String description;

    private String role_title;

    private int job_role_id;

    public int getCapability_id() {
        return capability_id;
    }

    public void setCapability_id(int capability_id) {
        this.capability_id = capability_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getJob_role_id() {
        return job_role_id;
    }

    public void setJob_role_id(int job_role_id) {
        this.job_role_id = job_role_id;
    }

    public String getRole_title() {
        return role_title;
    }

    public void setRole_title(String role_title) {
        this.role_title = role_title;
    }

    @JsonCreator
    public Capabilities(@JsonProperty("CapabilityId") int CapabilityId,
                        @JsonProperty("Name") String Name,
                        @JsonProperty("Description") String Description,
                        @JsonProperty("RoleTitle") String RoleTitle)
    {
        setCapability_id(CapabilityId);
        setName(Name);
        setDescription(Description);
        setRole_title(RoleTitle);

    }
}
