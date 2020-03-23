import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ArrayTaskList extends AbstractTaskList {
    private Task array[];
    private int pointer=0;

    public ArrayTaskList() {
        array = new Task[1];
    }



    @Override
    void add(Task task) {
        if(task!=null) {
            if (pointer == array.length - 1)
                resize(array.length * 2);
            array[pointer++] = task;
        }
        else
            throw new RuntimeException();
    }

    @Override
    void remove(Task task) {
        if (task!=null) {
            int index = 0;
            boolean fl = false;
            for (int i = 0; i < pointer; i++) {
                if (task.equals(array[i])) {
                    index = i;
                    fl = true;
                }
            }
            if (!fl) {
                return;
            }

            for (int i = index; i < pointer; i++) {
                array[i] = array[i + 1];
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
    int size() {
        return pointer;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (obj==null||this!=obj||getClass()!=obj.getClass())
//            return false;
//        if(this==obj)
//        {
//            return true;
//        }
//
//        ArrayTaskList atl =  (ArrayTaskList) obj;
//        boolean fl = true;
//        Iterator it1 = atl.iterator();
//        Iterator it2 = this.iterator();
//        while(it1.hasNext()&&it2.hasNext())
//        {
//            if (!it1.next().equals(it2.next()))
//                return false;
//        }
//        return true;
//    }


    //    @Override
//    public ArrayTaskList clone() throws CloneNotSupportedException {
//        try {
//            ArrayTaskList clone = (ArrayTaskList) super.clone();
//
//            return clone;
//        } catch (CloneNotSupportedException ex) {
//            throw new AssertionError(ex);
//        }
//
//    }


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
        Task[] newArray = new Task[newLength];
        System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
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
