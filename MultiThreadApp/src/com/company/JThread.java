package com.company;

public class JThread extends Thread {
    public JThread(String name){
        super(name);
    }

    public void run()
    {
        System.out.printf("Поток %s запустился\n", getName());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Поток %s завершился\n", Thread.currentThread().getName());
    }
}
