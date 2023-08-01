package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TrainingRequest {
    private String name;
    private String link;
    private String category;

    @JsonCreator
    public TrainingRequest(@JsonProperty("name") String name, @JsonProperty("link") String link, @JsonProperty("category") String category) {
        setName(name);
        setLink(link);
        setCategory(category);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}