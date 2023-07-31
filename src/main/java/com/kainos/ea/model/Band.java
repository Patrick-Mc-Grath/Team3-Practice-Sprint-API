package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Band {
    private int bandId;
    private String name;

    @JsonCreator
    public Band(@JsonProperty("bandId") int bandId, @JsonProperty("bandName") String name) {
        setBandId(bandId);
        setName(name);
    }

    public int getBandId() {
        return bandId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
