package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JobRoleResponse class.
 */
public class JobRoleResponse {
  private int roleId;
  private String roleTitle;
  private int bandId;
  private String bandName;
  private String jobFamilyName;
  private String capabilityName;

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

  public String getJobFamilyName() {
    return jobFamilyName;
  }

  public void setJobFamilyName(String jobFamilyName) {
    this.jobFamilyName = jobFamilyName;
  }

  public String getCapabilityName() {
    return capabilityName;
  }

  public void setCapabilityName(String capabilityName) {
    this.capabilityName = capabilityName;
  }

  /**
   * Constructor for JobRoleResponse.
   *
   * @param roleId role id
   *
   * @param roleTitle role title
   *
   * @param bandName band name
   *
   * @param jobFamilyName job family name
   *
   * @param capabilityName capability name
   */
  @JsonCreator
  public JobRoleResponse(
      @JsonProperty("role_id") int roleId,
      @JsonProperty("role_title") String roleTitle,
      @JsonProperty("band_id") int bandId,
      @JsonProperty("band_name") String bandName,
      @JsonProperty("job_family_name") String jobFamilyName,
      @JsonProperty("capability_name") String capabilityName) {
    setRoleId(roleId);
    setRoleTitle(roleTitle);
    setBandId(bandId);
    setBandName(bandName);
    setJobFamilyName(jobFamilyName);
    setCapabilityName(capabilityName);
  }
}
