package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JobRole class.
 */
public class JobRole {
  private int roleId;
  private String roleTitle;
  private int jobFamilyId;
  private String capabilityName;
  private int bandId;
  private String bandName;

  /**
   * JobRole constructor.
   *
   * @param roleId the role id
   *
   * @param roleTitle the role title
   *
   * @param jobFamilyId the job family id
   *
   * @param capabilityName the capability name
   *
   * @param bandId the band id
   *
   * @param bandName the band name
   */
  @JsonCreator
  public JobRole(
                 @JsonProperty("role_id") int roleId,
                 @JsonProperty("role_title") String roleTitle,
                 @JsonProperty("jobFamilyId") int jobFamilyId,
                 @JsonProperty("Capabilities.name") String capabilityName,
                 @JsonProperty("band_id") int bandId,
                 @JsonProperty("band_name") String bandName) {
    setRoleId(roleId);
    setRoleTitle(roleTitle);
    setJobFamilyId(jobFamilyId);
    setCapabilityName(capabilityName);
  }

  public int getBandId() {
    return bandId;
  }

  public void setBandId(int bandId) {
    this.bandId = bandId;
  }

  public String getBandName() {
    return bandName;
  }

  public void setBandName(String bandName) {
    this.bandName = bandName;
  }

  public int getRoleId() {
    return roleId;
  }

  public void setRoleId(int roleId) {
    this.roleId = roleId;
  }

  public String getRoleTitle() {
    return roleTitle;
  }

  public void setRoleTitle(String roleTitle) {
    this.roleTitle = roleTitle;
  }

  public int getJobFamilyId() {
    return jobFamilyId;
  }

  public void setJobFamilyId(int jobFamilyId) {
    this.jobFamilyId = jobFamilyId;
  }

  public String getCapabilityName() {
    return capabilityName;
  }

  public void setCapabilityName(String capabilityName) {
    this.capabilityName = capabilityName;
  }

}
