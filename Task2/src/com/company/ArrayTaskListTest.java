package com.company;

import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class ArrayTaskListTest extends TaskListTest {
    public ArrayTaskListTest(Class<? extends AbstractTaskList> tasksClass) {
        super(tasksClass);
    }

    @Before
    public void createTaskList() {
        tasks = new ArrayTaskList();
    }

    @Test
    public void getSizeArrayTaskList()
    {
        ArrayTaskList tl = new ArrayTaskList();

        assertEquals("size", 0,tl.size());
        Task ts = new Task("gg",0);
        tl.add(ts);
        assertEquals("size", 1,tl.size());
        tl.add(ts);
        tl.add(ts);
        assertEquals("size", 3,tl.size());
    }

    @Test
    public void getSizeAfterRemoveArrayTaskList()
    {
        ArrayTaskList tl = new ArrayTaskList();

        assertEquals("size", 0,tl.size());
        Task ts = new Task("gg",0);
        tl.add(ts);
        tl.add(ts);
        tl.add(ts);
        tl.remove(ts);
        assertEquals("size", 2,tl.size());
        tl.remove(ts);
        tl.remove(ts);
        assertEquals("size", 0,tl.size());
    }

    @Test
    public void EqualArrayTaskList()
    {
        ArrayTaskList tl = new ArrayTaskList();
        ArrayTaskList tl2 = new ArrayTaskList();
        Task ts = new Task("gg",0);
        tl.add(ts);
        tl.add(ts);
        tl.add(ts);
        tl2.add(ts);
        tl2.add(ts);
        tl2.add(ts);

        assertEquals("arrays must be equal", tl,tl2);
    }

}
