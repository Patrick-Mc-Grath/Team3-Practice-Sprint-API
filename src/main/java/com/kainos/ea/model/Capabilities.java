package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Capabilities {
    private int CapabilityId;
    private String Name;
    private String Description;

    public int getCapabilityId() {
        return CapabilityId;
    }

    public void setCapabilityId(int capabilityId) {
        CapabilityId = capabilityId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
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
