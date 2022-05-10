package com.example.bwfitness.model;

public class Model {
    String title;
    String desc;
    int icon;

    public Model(String title, String desc, int icon) {
        this.title=title;
        this.desc=desc;
        this.icon=icon;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public int getIcon() {
        return icon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
