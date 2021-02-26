package com.mphasis.main;

import java.util.Currency;

class Data{
    private int value;

    public synchronized void getValue() {
        //100
        System.out.println(value);
        try {
            notify();
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void setValue(int value) {

        this.value = value;
        try {
            notify();
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Runner implements Runnable{
    Data data;
    Runner(){
        data = new Data();
    }
    @Override
    public void run() {
        Thread currThread = Thread.currentThread();
        for (int counter=0;counter<10;counter++) {
            System.out.println(currThread);
            if (currThread.getName().equals("Producer")) {

                data.setValue(counter+1);
            }
            else{
                data.getValue();
            }
        }

    }
}

public class Main {

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Runner runner = new Runner();
        Thread thread1 = new Thread(runner,"Producer");
        Thread thread2 = new Thread(runner,"Consumer");

        thread1.start();
        thread2.start();

        System.out.println("End");

    }
}
