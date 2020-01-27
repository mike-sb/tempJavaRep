package com.company.model;



public class Lesson {
    private String time;
    private String name;
    private String room;

    public Lesson(String time, String name, String room) {
        setTime(time);
        setName(name);
        setRoom(room);
    }

    public Lesson() {
    }

    public String getDate() {
        return time;
    }

    public void setTime(String time) {
        try {
            if (time.length()!=5)
                throw new Exception("Время записывается в формате 'чч:мм' ");
        if(time.charAt(2)!=':')
        {
            throw new Exception("Время должно содержать ':' ");
        }
        if(time.charAt(0)<'0'||time.charAt(0)>'2'||time.charAt(1)<'0'||time.charAt(1)>'9'||(time.charAt(0)=='2'&&time.charAt(1)>'4'))
        {
                throw new Exception("Часы дожны быть в формате 00 - 23");
        }
        if(time.charAt(3)<'0'||time.charAt(3)>'5'||time.charAt(4)<'0'||time.charAt(4)>'9')
        {
                throw new Exception("Минуты дожны быть в формате 00 - 59");
        }
        this.time = time;

        } catch (Exception e) {
            e.printStackTrace();
        }

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
}
