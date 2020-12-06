package com.robotlab.expeditions2.model;

public class LessonImage {
    private int Id;
    private String url;
    private int lessonId;
    private int downloadId;
    private boolean status;

    public LessonImage(int id, String url, int lessonId) {
        Id = id;
        this.url = url;
        this.lessonId = lessonId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }
}
