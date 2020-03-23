package com.company;

public class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.printf("Поток %s запустился\n",  Thread.currentThread().getName());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Поток %s завершился\n", Thread.currentThread().getName());
    }
}
