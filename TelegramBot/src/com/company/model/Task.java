package com.company.model;

import java.util.Date;

public class Task {
    private Date date;
    private Lesson lesson;
    private String task;

    public Task(Date date, Lesson lesson, String task) {
        this.date = date;
        this.lesson = lesson;
        this.task = task;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
