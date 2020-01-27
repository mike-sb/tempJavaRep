package com.company.model;

import java.util.ArrayList;

public class Schedule {
    private WeekType weekType;
    private ArrayList<Day>  days;

    public Schedule(WeekType weekType) {
        setDays(new Day("Понедельник"));
        setDays(new Day("Вторник"));
        setDays(new Day("Среда"));
        setDays(new Day("Четверг"));
        setDays(new Day("Пятница"));
        setDays(new Day("Суббота"));
    }

    public WeekType getWeekType() {
        return weekType;
    }

    public void setWeekType(WeekType weekType) {
        this.weekType = weekType;
    }

    public ArrayList<Day> getDays() {
        return days;
    }

    public void setDays(Day day) {
        this.days.add(day);
    }
}
