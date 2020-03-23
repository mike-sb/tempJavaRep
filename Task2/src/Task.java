public class Task implements Cloneable{

    private String title;
    private int start;
    private int end;
    private int interval;
    private boolean active;


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
        if(time<0)
        {
            throw new RuntimeException();
        }
        setTime(time,time,0);

    }

    public void setTime(int start,int end,int interval) {
        if(start<0||end<start||end<0||interval<0)
        {
            throw new RuntimeException();
        }
    this.start = start;
    this.end = end;
    this.interval = interval;
        setActive(true);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title==null)
        {
            throw new RuntimeException();
        }
        this.title = title;
    }

    public int getStartTime() {
        return start;
    }

    public void setStartTime(int start) {
        if(start<0)
        {
            throw new RuntimeException();
        }
            this.start = start;

    }

    public int getEndTime() {
        return end;
    }

    public void setEndTime(int end) {
        if(end<start||end<0)
        {
            throw new RuntimeException();
        }
            this.end = end;
           // int count=start;
    }

    public int getRepeatInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        if(interval<0)
        {
            throw new RuntimeException();
        }
            this.interval = interval;
       setActive(true);
    }


    public Task(String title, int start) {
        if(start<0||title==null)
        {
            throw new RuntimeException();
        }
            this.title = title;
            setTime(start);
        active=false;
    }

    public Task(String title, int start, int end, int interval) {
        if(title==null||start<0||end<start||end<0||interval<0)
        {
            throw new RuntimeException();
        }
            this.title = title;
            setTime(start,end,interval);
        active=false;
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

//    @Override
//    public Task clone()
//    {
//        Task t = new Task(getTitle(),getStartTime(),getEndTime(), getRepeatInterval());
//        return t;
//    }

    @Override
    public Task clone() throws CloneNotSupportedException {
        try {
            Task clone = (Task)super.clone();
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new AssertionError(ex);
        }

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
        if (obj==null)
        {
            if (this!=null)
                return false;
            else {
                return true;
            }
        }
        if (this.getClass()!=obj.getClass())
            return false;


        Task task = (Task) obj;



        if (task == this)
            return true;
        else {
            if (task.getTitle() == getTitle() && task.getStartTime() == getStartTime() && task.getRepeatInterval() == getRepeatInterval() && task.getEndTime() == getEndTime() && task.isActive() == isActive()) {
                return true;
            }
            return false;
        }
    }
}
