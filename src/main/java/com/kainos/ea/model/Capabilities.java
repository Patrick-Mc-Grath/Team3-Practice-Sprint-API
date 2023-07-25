package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Capabilities {
    private int capabilityId;
    private String name;
    private String description;

    public int getCapabilityId() {
        return capabilityId;
    }

    public void setCapabilityId(int capabilityId) {
        capabilityId = capabilityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = description;
    }

    @JsonCreator
    public Capabilities(@JsonProperty("capability_id") int capabilityId,
                        @JsonProperty("name") String name,
                        @JsonProperty("description") String description)
    {
        setCapabilityId(capabilityId);
        setName(name);
        setDescription(description);

    }
}
