package com.company;

public class Car {
    private String model;
    private int cost;
    private int year;

    public Car(String model, int cost, int year) {
        this.model = model;
        this.cost = cost;
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
