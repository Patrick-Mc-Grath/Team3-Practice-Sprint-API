package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRole
{
    private int roleId;

    private String roleTitle;

    private String link;


    @JsonCreator
    public JobRole(
                   @JsonProperty("role_id") int roleId,
                   @JsonProperty("role_title") String roleTitle,
                   @JsonProperty("spec_link") String link)
    {
        setRoleId(roleId);
        setRoleTitle(roleTitle);
        setLink(link);
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


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
