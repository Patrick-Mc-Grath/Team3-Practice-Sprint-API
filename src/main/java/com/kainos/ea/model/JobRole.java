package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRole
{
    private int roleId;
    private String roleTitle;
    private String capabilityName;
    private int bandId;
    private String bandName;


    @JsonCreator
    public JobRole(
             @JsonProperty("role_id") int roleId,
             @JsonProperty("role_title") String roleTitle,
             @JsonProperty("Capabilities.name") String capabilityName,
             @JsonProperty("Role_Bands.band_id") int bandId,
             @JsonProperty("Role_Bands.band_name") String bandName
    ) {
        setRoleId(roleId);
        setRoleTitle(roleTitle);
        setCapabilityName(capabilityName);
        setBandId(bandId);
        setBandName(bandName);
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public int getBandId() {
    return bandId;
  }

  public void setBandId(int bandId) {
    this.bandId = bandId;
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
