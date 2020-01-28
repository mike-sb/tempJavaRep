package com.company.model;

import java.util.ArrayList;

public class Day {
    private int id;
    private String name;
    private ArrayList<Lesson> lessons;

    public Day(String name) {
        this.name = name;
    }

    public Day(String name, WeekType weekType) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
