package com.kainos.ea.cli;

public class CompetencyRequest {


    private String competencyName;
    private String description;
    private String bandName;


    public CompetencyRequest(String competencyName, String description, String bandName) {

        this.competencyName = competencyName;
        this.description = description;
        this.bandName = bandName;
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




