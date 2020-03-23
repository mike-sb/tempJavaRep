package com.company;

public class Task implements Cloneable {

    private String title;
    private int start;
    private int end;
    private int interval;
    private boolean active;

    public Task(String title, int start) {
       setTitle(title);
        setTime(start);

    }

    public Task(String title, int start, int end, int interval) {

        this.title = title;
        setTime(start,end,interval);

    }

    public boolean isActive() {
        return active;
    }
    public boolean isRepeated() {
        return interval>0;
    }

    public void setActive(boolean active) {

        this.active = active;
    }

    public int getTime() {

        return start;
    }
    public void setTime(int time) {
        setTime(time,time,0);

    }

    public void setTime(int start,int end,int interval) {
        if (end < start)
            throw new RuntimeException();
        this.start = start;
        this.end = end;
        this.interval = interval;
        setActive(true);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStartTime() {
        return start;
    }

    public void setStartTime(int start) {

            this.start = start;

    }

    public int getEndTime() {
        return end;
    }

    public void setEndTime(int end) {
            this.end = end;
           // int count=start;
    }

    public int getRepeatInterval() {
        return interval;
    }

    public void setInterval(int interval) {
            this.interval = interval;
       setActive(true);
    }




    @Override
    public String toString() {
        String str;
        if (!this.isActive()) {
            return String.format("Task %s is inactive",  getTitle());
        }
        else if (!isRepeated()) {
            return String.format("Task %s at %d",  getTitle(),getStartTime());
        }

        return String.format("Task %s from %d to %d every %d seconds", getTitle(),getStartTime(),getEndTime(),getRepeatInterval());
    }
    @Override
    public Task clone() {

        Task t = null;
        try {
            t = (Task) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return t;
    }
    @Override
    public int hashCode() {
        final int pr = 31;
        int result ;
        result = pr*title.hashCode()+ start*pr;
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj==null || this.getClass()!=obj.getClass())
            return false;

        Task task = (Task) obj;
        if (task == this)
            return true;
        else if (task.getTitle().equals(getTitle()) && task.getStartTime() == getStartTime() && task.getRepeatInterval() == getRepeatInterval()
                && task.getEndTime() == getEndTime() && task.isActive() == isActive())
                return true;
            return false;
    }
}
