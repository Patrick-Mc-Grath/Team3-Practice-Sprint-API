package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRole
{
    private int role_Id;
    private String role_title;
    private String spec_link;

    @JsonCreator
    public JobRole(
                   @JsonProperty("role_id") int roleId,
                   @JsonProperty("role_title") String role_title,
                   @JsonProperty("spec_link") String spec_link)
    {
        setRoleId(roleId);
        setRoleTitle(role_title);
        setSpecLink(spec_link);
    }

    public int getRoleId() {
        return role_Id;
    }

    public void setRoleId(int roleID) {
        this.role_Id = roleID;
    }

    public String getRoleTitle() {
        return role_title;
    }

    public void setRoleTitle(String roleTitle) {
        this.role_title = roleTitle;
    }

    public String getSpecLink() {
        return spec_link;
    }

    public void setSpecLink(String link) {
        this.spec_link = link;
    }
}
