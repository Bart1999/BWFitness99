package com.example.bwfitness.model;

public class Model {
    String title;
    String desc;
    int image;

    public Model(String title, String desc, int image) {
        this.title=title;
        this.desc=desc;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public int getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
