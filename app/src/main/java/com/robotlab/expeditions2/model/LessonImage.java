package com.robotlab.expeditions2.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class LessonImage {

    @PrimaryKey(autoGenerate = true)
    private int Id;
    private String url;
    private int lessonId;
    private int downloadId;
    private int status;

    public LessonImage() {
    }

    @Ignore
    public LessonImage(String url, int lessonId) {
        this.url = url;
        this.lessonId = lessonId;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public int getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(int downloadId) {
        this.downloadId = downloadId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
