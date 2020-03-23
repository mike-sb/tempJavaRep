import org.junit.*;
import static org.junit.Assert.*;

public class EqualsTaskTest {

    @Test
    public void testEqualsToItself() {
        Task task1 = new Task("Some", 10);
        assertEquals("Task must be equals to itself", task1, task1);
    }

    @Test
    public void testEquals() {
        Task task1 = new Task("Some", 10);
        Task task2 = new Task("Some", 10);
        System.out.println(task1.equals(task2));
        System.out.println(task2.equals(task1));
        assertEquals("Tasks, created with same constructor must be equals", task1, task2);
        assertEquals("a = b <=> b = a", task2, task1);
    }

    @Test
    public void testEqualsActivity() {
        Task task1 = new Task("Some", 10);
        Task task2 = new Task("Some", 1);
        task2.setActive(true);
        assertFalse("Active task couldn't equal to inactive", task1.equals(task2));
    }

    @Test
    public void testEqualsChanged() {
        Task task1 = new Task("Some", 100);
        Task task2 = new Task("Some", 100);
        task1.setTime(0, 50, 3);
        task2.setTime(0, 50, 3);
        System.out.println(task1.equals(task2));
        System.out.println(task2.equals(task1));
        assertEquals("Tasks with same state must equal", task1, task2);
    }

    @Test
    public void testEqualsNull() {
        Task task1 = new Task("Some", 10);
        assertFalse("any.equals(null) must be false", task1.equals(null));
    }

    @Test
    public void testEqualsToString() {
        Task task1 = new Task("Some", 10);
        assertFalse("Task can be equal only to task", task1.equals("some string"));
    }

    @Test
    public void testEqualsSymmetry() {
        Task egoist = new EgoistTask("Title", 10);
        Task simple = new Task("Title", 10);
        assertEquals("equals must be simmetric", egoist.equals(simple), simple.equals(egoist));
    }

    @Test
    public void testHashCode() {
        Task task1 = new Task("Some", 100);
        task1.setTime(0, 12, 3);
        task1.setActive(true);
        Task task2 = new Task("Some", 18);
        task2.setTime(0, 12, 3);
        task2.setActive(true);
        
        assertEquals(task1.hashCode(), task2.hashCode());
    }
}

class EgoistTask extends Task {
    public EgoistTask(String title, int time) {
        super(title, time);
    }
    public boolean equals(Object obj) {
        return false;
    }
}












