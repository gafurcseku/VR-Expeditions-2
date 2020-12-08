package com.robotlab.expeditions2.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class LessonImage {

    @PrimaryKey
    private int Id;
    private String url;
    private int lessonId;
    private int downloadId;
    private boolean status;

    public LessonImage() {
    }

    @Ignore
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
