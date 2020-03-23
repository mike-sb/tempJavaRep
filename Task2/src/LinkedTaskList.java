import javax.management.RuntimeMBeanException;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;


public class LinkedTaskList extends AbstractTaskList {
    class Node {
        public Task task;
        public Node next;

        public boolean hasNext() {
            if (next!=null) {
                return true;
            }
            return false;
        }
    }

    private Node head;
    private Node cur;
    private int size = 0;

    public LinkedTaskList() {
        head = null;
    }

    public LinkedTaskList(Task task) {
        add(task);
    }

    @Override
    void add(Task task) {
        if (task!=null) {

            Node node = new Node();
            node.task = task;
            node.next = null;

            if (head == null) {
                head = node;
                cur = head;
            } else {
                cur.next = node;
                cur = cur.next;
            }

            size++;
        }
        else throw new RuntimeException();
    }

    @Override
    void remove(Task task) {

        if(task!=null)
        {
            if (size==1)
            {
                head=null;
                cur = head;
            }
            else if(head.task.equals(task))
            {
                print();
                head = head.next;
                print();
            }
            else
            {
                int pointer =0;
                cur = head;
                while (cur!=null)
                {
                    pointer++;
                    if(cur.task.equals(task))
                    {
                        break;
                    }
                    cur=cur.next;
                }

                cur = head;
                int temp = 0;
                while(temp<=pointer-1)
                {
                    cur = cur.next;
                    temp++;
                }
                if(pointer<size-1)
                cur.next= cur.next.next;
                else
                    cur.next=null;

            }
        }else throw new RuntimeException();
        print();
        size--;



//        if (task!=null) {
//                if (size == 1) {
//                    head = null;
//                    cur = null;
//                } else {
//                    cur = head;
//                    if (cur.task.equals(task)) {
//                        head = head.next;
//                    } else {
//                        while (!cur.next.task.equals(task) && cur != null) {
//
//                            cur = cur.next;
//                        }
//                        if (cur.next == null) {
//                            cur = head;
//                            while (cur.next.next != null) {
//                                cur = cur.next;
//                            }
//                            cur.next = null;
//                        } else {
//                            cur.next.task = null;
//                            cur.next = cur.next.next;
//                        }
//                    }
//                }
//                size--;
//            } else throw new RuntimeException();

    }

    @Override
    int size() {
        return size;
    }

    @Override
    public int hashCode() {
        cur = head;
        int count = 1;
        int sum = 0;
        while (cur!=null)
        {
            sum+=cur.task.hashCode()*count*31;
            count++;
            cur = cur.next;
        }
        return sum;
    }


    void print()
 {
     cur = head;
     if (cur!=null) {
         while (cur != null) {
             System.out.println(cur.task);
             cur = cur.next;
         }
     }
     else
     {
         System.out.println("List is empty");
     }
 }

    Task[] incoming(int from, int to) {
        Task[] arr = new Task[0];
        cur = head;
        int count = 0;
        while (cur != null) {

            if (cur.task.getStartTime() == from && cur.task.getEndTime() == to) {
                count++;
                Task[] newArray = new Task[count];
                System.arraycopy(arr, 0, newArray, 0, count);
                arr = newArray;
                arr[count] = cur.task;
            }
            cur = cur.next;
        }

        return arr;
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
//
//        LinkedTaskList atl =  ( LinkedTaskList) obj;
//        boolean fl = true;
//        Iterator it1 = atl.iterator();
//        Iterator it2 = this.iterator();
//        while(it1.hasNext()&&it2.hasNext())
//        {
//            if (it1.next().equals(it2.next()))
//                return false;
//        }
//        return true;
//    }


    @Override
    public Iterator<Task> iterator() {
        return new Iterator<>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current.hasNext();
            }

            @Override
            public Task next() throws IndexOutOfBoundsException {
                Task result = current.task;
                if (!current.hasNext()) throw new IndexOutOfBoundsException("End of list.");
                current = current.next;
                return result;
            }
        };
    }


}


