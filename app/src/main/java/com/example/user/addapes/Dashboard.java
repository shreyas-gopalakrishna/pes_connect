package com.example.user.addapes;

public class Dashboard {

    String name;//description
    int id;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Dashboard(String name, int id) {
        this.name = name;
        this.id = id;
    }
}