package com.kainos.ea.model;

public class Training {
    private int trainingId;
    private String name;
    private String link;
    private String category;

    public Training(int trainingId, String name, String link, String category) {
        setTrainingId(trainingId);
        setName(name);
        setLink(link);
        setCategory(category);
    }

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
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
