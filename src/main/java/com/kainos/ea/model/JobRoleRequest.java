package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRoleRequest {
    private String roleTitle;
    private int bandId;
    private int jobFamilyId;

    public String getRoleTitle() {
        return roleTitle;
    }

    private void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public int getBandId() {
        return bandId;
    }

    private void setBandId(int bandId) {
        this.bandId = bandId;
    }

    public int getJobFamilyId() {
        return jobFamilyId;
    }

    private void setJobFamilyId(int jobFamilyId) {
        this.jobFamilyId = jobFamilyId;
    }

    @JsonCreator
    public JobRoleRequest(
            @JsonProperty("RoleTitle")String roleTitle,
            @JsonProperty("BandID")int bandId,
            @JsonProperty("JobFamilyID")int jobFamilyId) {
        setRoleTitle(roleTitle);
        setBandId(bandId);
        setJobFamilyId(jobFamilyId);
    }
}
