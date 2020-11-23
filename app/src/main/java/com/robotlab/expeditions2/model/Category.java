package com.robotlab.expeditions2.model;

public class Category {
    private int _id;
    private String name;
    private Boolean selected;

    public Category() {

    }

    public Category(int _id, String name, Boolean selected) {
        this._id = _id;
        this.name = name;
        this.selected = selected;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
