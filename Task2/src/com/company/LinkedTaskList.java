package com.company;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;


public class LinkedTaskList extends AbstractTaskList {
    class Node{
        public Task task;
        public Node next;

        public boolean hasNext() {
            return next != null;
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
    public void add(Task task) {
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
    }

    @Override
    public void remove(Task task) {
        if (task!=null) {
            try {
                if (size == 1) {
                    head = null;
                    cur = null;
                } else {
                    cur = head;
                    if (cur.task.equals(task)) {
                        head = head.next;
                    } else {
                        while (!cur.next.task.equals(task) && cur != null) {

                            cur = cur.next;
                        }
                        if (cur.next == null) {
                            cur = head;
                            while (cur.next.next != null) {
                                cur = cur.next;
                            }
                            cur.next = null;
                        } else {
                            cur.next.task = null;
                            cur.next = cur.next.next;
                        }
                    }
                }
                size--;
            } catch (NullPointerException e) {
                throw e;
            }
        }
    }

    //
    @Override
    public int size() {
        return size;
    }

    @Override
    public int hashCode() {
        Node p = head;
        int count = 1;
        int sum = 0;
        while (p!=null)
        {
            sum += p.task.hashCode() * count * 31;
            count++;
            p = p.next;
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

    public Task[] incoming(int from, int to) {
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


