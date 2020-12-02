package com.robotlab.expeditions2.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Expedition implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int _id;
    //https://cdn.slashgear.com/wp-content/uploads/2020/05/deadly-wallpaper-1280x720.jpg
    private String image_url;
    private String title;
    private String description;
    private int category;
    private String lesson;
    private String grade;
    private String type;
    private String pdfUrl;
    private String pdfName;

    public Expedition(){

    }

    @Ignore
    public Expedition(int _id,String image_url, String title, String description, String lesson, String grade, String type) {
        this._id = _id;
        this.image_url = image_url;
        this.title = title;
        this.description = description;
        this.lesson = lesson;
        this.grade = grade;
        this.type = type;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getPdfName() {
        return pdfName;
    }

    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }
}
