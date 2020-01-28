package com.company.model;

import java.util.ArrayList;

public class Faculty {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String name;
    private ArrayList<Group> groups;

    public Faculty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public void addGroup(Group group)
    {
        groups.add(group);
    }
}
