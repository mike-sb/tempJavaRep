package com.company.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    private int id;
    private String date;
    private String  lesson;
    private String task;

    public Task(String date, String lesson, String task) throws Exception {
      setDate(date);
        setLesson(lesson);
        setTask(task);
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date) throws Exception {//31.12.2020
        Exception e;
            if (date.length()!=10||!checkDate(date))
                throw e=  new Exception("Дата записывается в формате 'ДД.ММ.ГГГГ' ");
            if(date.charAt(0)=='3'&&date.charAt(3)=='0'&&date.charAt(4)=='2')
            {
                throw e = new Exception("В феврале не может быть больше 29 дней");
            }
            this.date = date;
    }

    public String  getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private static boolean checkDate(String date){
        Pattern p = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])\\.(0?[1-9]|1[012])\\.((19|20)\\d\\d)$");
        Matcher m = p.matcher(date);
        return m.matches();
    }
}
