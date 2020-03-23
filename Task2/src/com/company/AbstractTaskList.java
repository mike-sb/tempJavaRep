package com.company;

import java.lang.Iterable;
import java.util.Arrays;
import java.util.Iterator;

public abstract class AbstractTaskList<clone> implements Iterable<Task>, Cloneable {

    public abstract void add(Task task);
    public abstract void remove(Task task);
    public abstract int size();


   // @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(this.getClass().getSimpleName());
//        sb.append(" [");
//        for (Task t : this) {
//            sb.append(t.getTitle());
//
//            if (i != size-1)
//                sb.append(", ");
//        }
//
//        sb.append("]");
//
//        return sb.toString();
//    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        for (Task t : this)
            sb.append(" " + t);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==null)
        return false;
        if (this == obj)
            return true;

        if (obj.getClass() != this.getClass())
            return false;

        AbstractTaskList atl =  (AbstractTaskList) obj;
        Iterator it1 = atl.iterator();
        Iterator it2 = this.iterator();
        while(it1.hasNext()&&it2.hasNext())
        {
            if (it1.next()!=it2.next())
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
