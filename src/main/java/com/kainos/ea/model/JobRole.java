package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRole
{
    private int roleId;
    private String roleTitle;
    private int job_family_id;
    private String capabilityName;

    @JsonCreator
    public JobRole(
                   @JsonProperty("role_id") int roleId,
                   @JsonProperty("role_title") String roleTitle,
                   @JsonProperty("job_family_id") int job_family_id,
                   @JsonProperty("Capabilities.name") String capabilityName)
    {
        setRoleId(roleId);
        setRoleTitle(roleTitle);
        setJob_family_id(job_family_id);
        setCapabilityName(capabilityName);
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleID) {
        this.roleId = roleID;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public int getJob_family_id() {
        return job_family_id;
    }

    public void setJob_family_id(int job_family_id) {
        this.job_family_id = job_family_id;
    }
    public String getCapabilityName() {return capabilityName;}

    public void setCapabilityName(String capabilityName) {this.capabilityName = capabilityName; }

}
