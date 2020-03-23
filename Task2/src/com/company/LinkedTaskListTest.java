package com.company;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class LinkedTaskListTest extends TaskListTest {

    public LinkedTaskListTest(Class<? extends AbstractTaskList> tasksClass) {
        super(tasksClass);
    }

    @Before
    public void before()
    {
        tasks = new LinkedTaskList();
    }

    @Test
    public void addNodeTest()
    {
        LinkedTaskList ltl = new LinkedTaskList();
        Task ts1 = new Task("ttt",0);
        Task ts2 = new Task("tt2",2);
        Task ts3 = new Task("tt3",4);
        ltl.add(ts1);
        assertEquals("size1",1,ltl.size());
        ltl.add(ts2);
        ltl.add(ts3);
        assertEquals("size3",3,ltl.size());
    }

    @Test
    public void removeMiddleNodeTest()
    {
        LinkedTaskList ltl = new LinkedTaskList();
        Task ts1 = new Task("ttt",0);
        Task ts2 = new Task("tt2",2);
        Task ts3 = new Task("tt3",4);
        ltl.add(ts1);
        ltl.add(ts2);
        ltl.add(ts3);
        ltl.print();
        System.out.println("removing");
        ltl.remove(ts2);
        assertEquals("size3",2,ltl.size());
    }
    @Test
    public void removeFirstNodeTest()
    {
        LinkedTaskList ltl = new LinkedTaskList();
        Task ts1 = new Task("ttt",0);
        Task ts2 = new Task("tt2",2);
        Task ts3 = new Task("tt3",4);
        ltl.add(ts1);
        ltl.add(ts2);
        ltl.add(ts3);
        ltl.print();
        System.out.println("removing");
        ltl.remove(ts1);
        assertEquals("size3",2,ltl.size());
    }

    @Test
    public void removeLastNodeTest()
    {
        LinkedTaskList ltl = new LinkedTaskList();
        Task ts1 = new Task("ttt",0);
        Task ts2 = new Task("tt2",2);
        Task ts3 = new Task("tt3",4);
        ltl.add(ts1);
        ltl.add(ts2);
        ltl.add(ts3);
        ltl.print();
        System.out.println("removing");
        ltl.remove(ts3);
        assertEquals("size3",2,ltl.size());
    }

    @Test
    public void printingTest()
    {
        LinkedTaskList ltl = new LinkedTaskList();
        Task ts1 = new Task("ttt",0);
        Task ts2 = new Task("tt2",2);
        Task ts3 = new Task("tt3",4);
        ltl.add(ts1);
        ltl.add(ts2);
        ltl.add(ts3);
        ltl.print();
    }

}
