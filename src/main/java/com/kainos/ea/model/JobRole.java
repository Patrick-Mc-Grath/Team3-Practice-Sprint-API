package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRole
{
    private int roleID;

    private String roleTitle;

    private String specLink;

    private String specSummary;

    private String capabilityName;

    @JsonCreator
    public JobRole(@JsonProperty("role_id") int roleID,
                   @JsonProperty("role_title") String roleTitle,
                   @JsonProperty("name") String capabilityName,
                   @JsonProperty("spec_link") String specLink,
                   @JsonProperty("spec_summary") String specSummary)

    {
        setRoleID(roleID);
        setRoleTitle(roleTitle);
        setCapabilityName(capabilityName);
        setSpecLink(specLink);
        setSpecSummary(specSummary);
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
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

    public void setSpecLink(String specLink) {
        this.specLink = specLink;
    }

    public String getSpecSummary() {
        return specSummary;
    }

    public void setSpecSummary(String specSummary) {
        this.specSummary = specSummary;
    }

    public String getCapabilityName() {
        return capabilityName;
    }

    public void setCapabilityName(String capabilityName) {
        this.capabilityName = capabilityName;
    }
}
