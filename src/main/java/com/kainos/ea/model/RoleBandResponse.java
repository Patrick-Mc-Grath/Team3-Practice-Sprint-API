package com.kainos.ea.model;

public class RoleBandResponse {
    private int roleID;
    private int bandID;
    private String roleName;
    private String bandName;

    public int getRoleID() {
        return roleID;
    }

    public int getBandID() {
        return bandID;
    }

    public String getRoleName() {
        return roleName;
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

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public RoleBandResponse(int roleID, int bandID, String roleName, String bandName) {
        setRoleID(roleID);
        setBandID(bandID);
        setRoleName(roleName);
        setBandName(bandName);
    }
}
