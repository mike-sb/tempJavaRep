package com.company.model;


public class Lesson {
    private int id;
    private String time;
    private String name;
    private String room;
    private WeekType weekType;
    private Day day;

    public Lesson( String name,String time, String room,WeekType weekType, Day day) throws Exception {
        setTime(time);
        setName(name);
        setRoom(room);
        setWeekType(weekType);
        setDay(day);
    }//name, time, room, weektype, day

    public Lesson() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) throws Exception {

        if (time.length() != 5)
            throw new Exception("Время записывается в формате 'чч:мм' ");
        if (time.charAt(2) != ':') {
            throw new Exception("Время должно содержать ':' ");
        }
        if (time.charAt(0) < '0' || time.charAt(0) > '2' || time.charAt(1) < '0' || time.charAt(1) > '9' || (time.charAt(0) == '2' && time.charAt(1) > '4')) {
            throw new Exception("Часы дожны быть в формате 00 - 23");
        }
        if (time.charAt(3) < '0' || time.charAt(3) > '5' || time.charAt(4) < '0' || time.charAt(4) > '9') {
            throw new Exception("Минуты дожны быть в формате 00 - 59");
        }
        this.time = time;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WeekType getWeekType() {
        return weekType;
    }

    public void setWeekType(WeekType weekType) {
        this.weekType = weekType;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
