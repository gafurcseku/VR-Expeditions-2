package com.robotlab.expeditions2.model;

import java.util.List;

public class Lesson {
    private int Id;
    private String title;
    private String subtitle;
    private String thumb;
    private int expeditionId;


    public Lesson(int id, String title, String subtitle, String thumb) {
        Id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.thumb = thumb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

}
