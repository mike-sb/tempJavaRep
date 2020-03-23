package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Главный поток запустился");

        ArrayList<JThread> threads = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            //Thread thread = new Thread(new MyThread(), "оток");

            JThread thread = new JThread("мой " + i);
            thread.start();
            threads.add(thread);
        }

        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).join();
        }

        System.out.println("Главный поток завершился\n");

    }
}
