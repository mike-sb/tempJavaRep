import java.lang.Iterable;
import java.util.Arrays;
import java.util.Iterator;

public abstract class AbstractTaskList<clone> implements Iterable<Task>, Cloneable {

    abstract void add(Task task);
    abstract void remove(Task task);
    abstract int size();

    @Override
    public  int hashCode()
    {
       return 31;
    }

    @Override
    public String toString()
    {
        String s =String.format("%s ",getClass());
        for (Task t : this)
        {
            s+=String.format("%s ",t.toString());
        }
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==null||getClass()!=obj.getClass())
            return false;
        if(this==obj)
        {
            return true;
        }

        AbstractTaskList atl =  (AbstractTaskList) obj;
        boolean fl = true;
        Iterator it1 = atl.iterator();
        Iterator it2 = this.iterator();
        while(it1.hasNext()&&it2.hasNext())
        {
            if (!it1.next().equals(it2.next()))
                return false;
        }
        return true;
    }

    @Override
    public AbstractTaskList clone() throws CloneNotSupportedException {
        try {
            AbstractTaskList clone = (AbstractTaskList)super.clone();

            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new AssertionError(ex);
        }

    }


}
