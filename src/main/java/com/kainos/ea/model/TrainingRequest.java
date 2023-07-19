package com.kainos.ea.model;

public class TrainingRequest {
    private String name;
    private String link;
    private String category;

    public TrainingRequest(String name, String link, String category) {
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
