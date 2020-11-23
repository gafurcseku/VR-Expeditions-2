package com.robotlab.expeditions2.model;

public class Expedition {
    private int _id;
    private String title;
    private String shortTitle;
    private String lesson;
    private String grade;
    private String type;

    public Expedition(){

    }

    public Expedition(int _id, String title, String shortTitle, String lesson, String grade, String type) {
        this._id = _id;
        this.title = title;
        this.shortTitle = shortTitle;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
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
}
