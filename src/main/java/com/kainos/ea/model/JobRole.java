package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRole {

    private int role_Id;

    private String role_title;

    private String spec_link;

    private String spec_summary;

    private String capability_name;

    public int getRole_Id() {
        return role_Id;
    }

    public void setRole_Id(int role_Id) {
        this.role_Id = role_Id;
    }

    public String getRoleTitle() {
        return role_title;
    }

    public void setRole_title(String role_title) {
        this.role_title = role_title;
    }

    public String getSpec_link() {
        return spec_link;
    }

    public void setSpec_link(String spec_link) {
        this.spec_link = spec_link;
    }

    public String getSpec_summary() {
        return spec_summary;
    }

    public void setSpec_summary(String spec_summary) {
        this.spec_summary = spec_summary;
    }

    public String getCapability_name() {
        return capability_name;
    }

    public void setCapability_name(String capability_name) {
        this.capability_name = capability_name;
    }

    @JsonCreator
    public JobRole(@JsonProperty("role_id") int roleID,
                   @JsonProperty("role_title") String roleTitle,
                   @JsonProperty("name") String capabilityName,
                   @JsonProperty("spec_link") String specLink,
                   @JsonProperty("spec_summary") String specSummary) {
        
        setRole_Id(roleID);
        setRole_title(roleTitle);
        setCapability_name(capabilityName);
        setSpec_link(specLink);
        setSpec_summary(specSummary);
    }
}
