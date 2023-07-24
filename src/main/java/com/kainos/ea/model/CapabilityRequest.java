package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CapabilityRequest {

private String name;
private String description;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonCreator
    public CapabilityRequest(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description) {
        this.setName(name);
        this.setDescription(description);
    }
}
