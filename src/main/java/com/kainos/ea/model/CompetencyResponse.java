package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CompetencyResponse {

    private String competencyName;
    private String description;
    private String bandName;


    @JsonCreator
    public CompetencyResponse(
            @JsonProperty("competencyName") String competencyName,
            @JsonProperty("description") String description,
            @JsonProperty("bandName") String bandName) {
        this.setCompetencyName(competencyName);
        this.setDescription(description);
        this.setBandName(bandName);
    }

    public String getCompetencyName() {
        return competencyName;
    }

    public void setCompetencyName(String competencyName) {
        this.competencyName = competencyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }
}