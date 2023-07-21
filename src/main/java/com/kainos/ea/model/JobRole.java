package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRole
{
    private int roleId;
    private String roleTitle;
    private String specLink;
    private String capabilityName;

    @JsonCreator
    public JobRole(
                   @JsonProperty("role_id") int roleId,
                   @JsonProperty("role_title") String roleTitle,
                   @JsonProperty("Capabilities.name") String capabilityName,
                   @JsonProperty("spec_link") String specLink)

    {
        setRoleId(roleId);
        setRoleTitle(roleTitle);
        setSpecLink(specLink);
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

    public String getSpecLink() {
        return specLink;
    }

    public void setSpecLink(String link) {
        this.specLink = link;
    }

    public String getCapabilityName() {return capabilityName;}

    public void setCapabilityName(String capabilityName) {this.capabilityName = capabilityName; }
}
