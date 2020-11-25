package com.robotlab.expeditions2.model;

public class Devices {
    private int id;
    private String name;
    private  Boolean online;

    public Devices(int id, String name, Boolean online) {
        this.id = id;
        this.name = name;
        this.online = online;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }
}
