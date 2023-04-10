package com.franky.designpattern.Adapter;

import java.util.concurrent.Callable;

class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("I'm called.");
        return null;
    }
}

public class Adapter implements Runnable {

    private final Callable<?> callable;

    public Adapter(Callable<?> callable) {
        this.callable = callable;
    }

    @Override
    public void run() {
        try {
            callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Callable<Integer> callable = new Task();
        Thread thread = new Thread(new Adapter(callable));
        //Thread thread = new Thread(callable);
        thread.start();
        Thread.sleep(1000);
    }
}
