package com.company;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ArrayTaskList extends AbstractTaskList {
    private Task[] array;
    private int pointer=0;

    private static final int DEFAULT_CAPACITY = 10;

    public ArrayTaskList() {
        array = new Task[DEFAULT_CAPACITY];
    }

    public ArrayTaskList(int capacity) {
        array = new Task[capacity];
    }

    @Override
    public ArrayTaskList clone() throws CloneNotSupportedException {
        try {
            ArrayTaskList clone = (ArrayTaskList) super.clone();

            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new AssertionError(ex);
        }

    }
    @Override
    public void add(Task task) {
        if(task!=null) {
            if (pointer == array.length - 1)
                resize(array.length * 2);
            array[pointer++] = task;
        }
        else
            throw new RuntimeException();
    }

    //переп
    @Override
    public void remove(Task task) {
        if (task!=null) {
            int index = 0;
            boolean fl = false;
            for (int i = 0; i < pointer; i++) {
                if (task.equals(array[i])) {
                    index = i;
                    for (int j = index; j < pointer-1; j++) {
                        array[j] = array[j + 1];
                    }
                    fl = true;
                }
            }
            if (!fl) {
                return;
            }


            array[pointer] = null;
            pointer--;
            if (array.length > (pointer + 1) * 2)
                resize(array.length / 2);
        }
        else
            throw new RuntimeException();
    }

    @Override
    public  int size() {
        return pointer;
    }

    @Override
    public int hashCode() {
        int sum=0;
        for (int i= 0; i<pointer;i++)
        {
            sum+=array[i].hashCode();
        }
        return sum;
    }


    private void resize(int newLength)
    {
        array = Arrays.copyOf(array, newLength);
    }

    @Override
    public Iterator<Task> iterator() {
        Iterator<Task> it = new Iterator<Task>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size() && array[currentIndex] != null;
            }

            @Override
            public Task next() {
                System.out.println(currentIndex);
                return array[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}
