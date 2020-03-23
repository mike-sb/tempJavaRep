import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class EqualsListTest extends AbstractTaskListTest {

    public EqualsListTest(Class<? extends AbstractTaskList> tasksClass) {
        super(tasksClass);
    }
    
    @Test
    public void testEquals() throws Exception {
        Task[] elements = {task("A"), task("B"), task("C")};

        AbstractTaskList list1 = createList();
        AbstractTaskList list2 = createList();

        for (Task task : elements) {
            list1.add(task);
            list2.add(task);
        }

        assertEquals(getTitle(), list1, list2);
        assertEquals(getTitle(), list2, list1);
    }
    
    @Test
    public void testEqualsNull() {
        assertFalse(getTitle(), tasks.equals(null));
    }
    
    @Test
    public void testIdentityEquals() {
        assertEquals(getTitle(), tasks, tasks);
    }
    
    @Test
    public void testSymmetricEquals() {
        AbstractTaskList dummy = new DummyList();
        System.out.println(tasks.equals(dummy));
        assertEquals(getTitle(), tasks.equals(dummy), dummy.equals(tasks));
    }
    
    @Test
    public void testHashCode() throws Exception {
        Task[] elements = {task("A"), task("B"), task("C")};

        AbstractTaskList list1 = createList();
        AbstractTaskList list2 = createList();
        for (Task task : elements) {
            list1.add(task);
            list2.add(task);
        }

      int hc1 =list1.hashCode();
        int hc2 =list2.hashCode();
        System.out.println(hc1==hc2);
        assertEquals(getTitle(),true, hc1==hc2);
    }
}

class DummyList extends AbstractTaskList {
    
    public void add(Task task) { }
    
    public void remove(Task task) { }
    
    public int size() { 
        return 0; 
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public Task getTask(int index) { 
        throw new IndexOutOfBoundsException();
    }
    
    public Iterator<Task> iterator() {
        return Collections.<Task>emptyList().iterator();
    }

    public boolean equals(Object obj) {
        return false;
    }
}












