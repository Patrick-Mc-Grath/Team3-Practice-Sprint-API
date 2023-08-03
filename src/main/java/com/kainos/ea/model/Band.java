package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Band {
    private int bandId;
    private String bandName;

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

    @JsonCreator
    public Band(
            @JsonProperty("band_id") int bandId,
            @JsonProperty("band_name") String bandName) {
        setBandId(bandId);
        setBandName(bandName);
    }
}
