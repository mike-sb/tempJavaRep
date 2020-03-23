package com.company;

import org.junit.*;
import static org.junit.Assert.*;

public class TaskTest {

    @Test
    public void testTitle() {
        Task task = new Task("test", 0);
        assertEquals("test", task.getTitle());
        task.setTitle("other");
        assertEquals("other", task.getTitle());
    }

    @Test
    public void testActive() {
        Task task = new Task("test", 0);
        assertFalse(task.isActive());
        task.setActive(true);
        assertTrue(task.isActive());
    }
    @Test
    public void testConstructorNonRepeated() {
        Task task = new Task("test", 10);
        assertFalse("active", task.isActive());
        assertEquals("time", 10, task.getTime());
        assertEquals("start", 10, task.getStartTime());
        assertEquals("end", 10, task.getEndTime());
        assertFalse("repeated", task.isRepeated());
    }
    @Test
    public void testConstructorRepeated() {
        Task task = new Task("test", 10, 100, 5);
        assertFalse("active", task.isActive());
        assertEquals("time", 10, task.getTime());
        assertEquals("start", 10, task.getStartTime());
        assertEquals("end", 100, task.getEndTime());
        assertTrue("repeated", task.isRepeated());
        assertEquals("repeatInterval", 5, task.getRepeatInterval());
    }
    @Test
    public void testTimeNonRepeated() {
        Task task = new Task("test", 0, 100, 15);
        task.setTime(50);
        assertEquals("time", 50, task.getTime());
        assertEquals("start", 50, task.getStartTime());
        assertEquals("end", 50, task.getEndTime());
        assertFalse("repeated", task.isRepeated());
    }
    @Test
    public void testTimeRepeated() {
        Task task = new Task("test", 10);
        task.setTime(5, 20, 1);
        assertEquals("time", 5, task.getTime());
        assertEquals("start", 5, task.getStartTime());
        assertEquals("end", 20, task.getEndTime());
        assertTrue("repeated", task.isRepeated());
        assertEquals("repeatInterval", 1, task.getRepeatInterval());
    }

    @Test(expected = RuntimeException.class)
    public void testWrongTitle() {
        Task task = new Task(null, 0);
    }

    @Test(expected = RuntimeException.class)
    public void testWrongTitle2() {
        Task task = new Task("OK", 0);
        task.setTitle(null);
    }


    @Test(expected = RuntimeException.class)
    public void testWrongTime() {
        Task task = new Task("Title", -1);
    }

    @Test(expected = RuntimeException.class)
    public void testWrongStart() {
        Task task = new Task("Title", -1, 10, 5);
    }

    @Test(expected = RuntimeException.class)
    public void testWrongEnd() {
        Task task = new Task("Title", 10, 6, 3);
    }

    @Test(expected = RuntimeException.class)
    public void testWrongInterval() {
        Task task = new Task("Title", 10, 100, -1);
    }

    @Test(expected = RuntimeException.class)
    public void testWrongSetTime() {
        Task task = new Task("Title", 0);
        task.setTime(-1);
    }

    @Test(expected = RuntimeException.class)
    public void testWrongSetStart() {
        Task task = new Task("Title", 0);
        task.setTime(-1, 10, 3);
    }

    @Test(expected = RuntimeException.class)
    public void testWrongSetEnd() {
        Task task = new Task("Title", 10);
        task.setTime(10, 6, 3);
    }

    @Test(expected = RuntimeException.class)
    public void testWrongSetInterval() {
        Task task = new Task("Title", 10);
        task.setTime(10, 100, -1);
    }
    @Test

    public void getHashCode()
    {
        Task task = new Task("a",10);
        assertEquals("hashSum", 3317, task.hashCode());
    }
     @Test
    public void getEqualResult()
     {
         Task task1 = new Task("a",10);
         Task task2 = new Task("a",10);
         assertTrue("repeated", task1.equals(task2));
     }
}



