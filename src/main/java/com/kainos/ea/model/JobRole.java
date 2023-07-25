package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRole
{
    private int roleId;
    private String roleTitle;
    private String capabilityName;


    @JsonCreator
    public JobRole(
                   @JsonProperty("role_id") int roleId,
                   @JsonProperty("role_title") String roleTitle,
                   @JsonProperty("Capabilities.name") String capabilityName)
                

    {
        setRoleId(roleId);
        setRoleTitle(roleTitle);
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

    public String getCapabilityName() {return capabilityName;}

    public void setCapabilityName(String capabilityName) {this.capabilityName = capabilityName; }
}
