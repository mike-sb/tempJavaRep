package com.company;

import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.core.IsCollectionContaining.*;
import static org.hamcrest.internal.ArrayIterator.*;


public class TaskListTest extends AbstractTaskListTest {

    public TaskListTest(Class<? extends AbstractTaskList> tasksClass) {
        super(tasksClass);
    }

    // tests --------------------------------------------------------------

    @Test
    public void testSizeAddGet() {
        assertEquals(getTitle(), 0, tasks.size());
        Task[] ts = {task("A"), task("B"), task("C")};
        addAll(ts);
        assertEquals(getTitle(), ts.length, tasks.size());
       // assertContains(ts);

    }

    @Test(expected = RuntimeException.class)
    public void testWrongAdd() {
        tasks.add(null);
    }

    @Test
    public void testRemove() {
        Task[] ts = {task("A"),task("B"),task("C"),task("D"),task("E")};
        addAll(ts);

        // remove first
        tasks.remove(task("A"));

        assertContains(new Task[] { ts[1], ts[2], ts[3], ts[4] });
        System.out.println(tasks.toString());
        // remove last
        tasks.remove(task("E"));
       assertContains(new Task[] { ts[1], ts[2], ts[3] });

        // remove middle
        tasks.remove(task("C"));
        assertContains(new Task[] { ts[1], ts[3] });
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidRemove() {
        tasks.remove(null);
    }

    @Test
    public void testAddManyTasks() {
        Task[] ts = new Task[1000];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = new Task("Task#"+ i, i);
            tasks.add(ts[i]);
        }
        assertEquals("size",1000,tasks.size());
        assertContains(ts);
    }

}



