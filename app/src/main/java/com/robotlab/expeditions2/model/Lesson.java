package com.robotlab.expeditions2.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class Lesson {
    @PrimaryKey
    private int Id;
    private String title;
    private String subtitle;
    private String thumb;
    private String image;
    private int expeditionId;
    private int status;
    private int downloadId;

    @Ignore
    private Boolean isClock = false;

    public Lesson() {
    }

    @Ignore
    public Lesson(int id, String title, String subtitle, String thumb, String image, int expeditionId) {
        Id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.thumb = thumb;
        this.image = image;
        this.expeditionId = expeditionId;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public int getExpeditionId() {
        return expeditionId;
    }

    public void setExpeditionId(int expeditionId) {
        this.expeditionId = expeditionId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Boolean getClock() {
        return isClock;
    }

    public void setClock(Boolean clock) {
        isClock = clock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(int downloadId) {
        this.downloadId = downloadId;
    }
}
