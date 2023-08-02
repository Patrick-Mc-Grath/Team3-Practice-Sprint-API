package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRoleResponse {
    private int roleId;
    private String roleTitle;
    private String bandName;
    private String jobFamilyName;
    private String capabilityName;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getJobFamilyName() {
        return jobFamilyName;
    }

    public void setJobFamilyName(String jobFamilyName) {
        this.jobFamilyName = jobFamilyName;
    }

    public String getCapabilityName() {
        return capabilityName;
    }

    public void setCapabilityName(String capabilityName) {
        this.capabilityName = capabilityName;
    }

    @JsonCreator
    public JobRoleResponse(
            @JsonProperty("role_id") int roleId,
            @JsonProperty("role_title") String roleTitle,
            @JsonProperty("band_name") String bandName,
            @JsonProperty("job_family_name") String jobFamilyName,
            @JsonProperty("capability_name") String capabilityName) {
        setRoleId(roleId);
        setRoleTitle(roleTitle);
        setBandName(bandName);
        setJobFamilyName(jobFamilyName);
        setCapabilityName(capabilityName);
    }
}