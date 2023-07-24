package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleBandResponse {
    private int roleID;
    private int bandID;
    private String roleTitle;
    private String bandName;

    public int getRoleID() {
        return roleID;
    }

    public int getBandID() {
        return bandID;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public String getBandName() {
        return bandName;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public void setBandID(int bandID) {
        this.bandID = bandID;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }


    @JsonCreator
    public RoleBandResponse(
            @JsonProperty("role_id") int roleID,
            @JsonProperty("band_id") int bandID,
            @JsonProperty("role_title") String roleTitle,
            @JsonProperty("band_name") String bandName) {
        setRoleID(roleID);
        setBandID(bandID);
        setRoleTitle(roleTitle);
        setBandName(bandName);
    }
}
