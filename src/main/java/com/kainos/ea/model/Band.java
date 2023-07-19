package com.kainos.ea.model;

public class Band {
    private int bandId;
    private String name;

    public Band(int bandId, String name) {
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
