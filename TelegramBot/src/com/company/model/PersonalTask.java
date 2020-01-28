package com.company.model;

public class PersonalTask extends Task{
    private String user_id;

    public PersonalTask(String date, String lesson, String task) throws Exception {
        super(date, lesson, task);

    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
