package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRole
{
    private int roleID;

    private String roleTitle;

    private double salary;

    @JsonCreator
    public JobRole(@JsonProperty("salary") double salary,
                   @JsonProperty("roleID") int roleID,
                   @JsonProperty("role_title") String roleTitle )
    {
        setRoleID(roleID);
        setRoleTitle(roleTitle);
        setSalary(salary);
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
