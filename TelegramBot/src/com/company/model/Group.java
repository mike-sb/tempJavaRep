package com.company.model;

import java.util.ArrayList;

public class Group {
    private int id;
   private String group_name;
   private ArrayList<Task> tasks;
   private User admin;
   private ArrayList<User> groupmates;
   private Schedule scheduleOdd;
   private Schedule scheduleEven;
   private int semester;

    public Group(String group_name, User admin) {
        this.group_name = group_name;
        this.admin = admin;
    }
    public Group(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_name() {
        return group_name;
    }


    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }
    public void addTask(Task task)
    {
        this.tasks.add(task);
    }

    public ArrayList<User> getGroupmates() {
        return groupmates;
    }

    public void setGroupmates(ArrayList<User> groupmates) {
        this.groupmates = groupmates;
    }

    public void addUser(User user)
    {
        groupmates.add(user);
    }

    public Schedule getScheduleOdd() {
        return scheduleOdd;
    }

    public void setScheduleOdd(Schedule scheduleOdd) {
        this.scheduleOdd = scheduleOdd;
    }

    public Schedule getScheduleEven() {
        return scheduleEven;
    }

    public void setScheduleEven(Schedule scheduleEven) {
        this.scheduleEven = scheduleEven;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
