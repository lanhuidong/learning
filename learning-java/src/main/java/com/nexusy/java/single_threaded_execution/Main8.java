package com.nexusy.java.single_threaded_execution;

public class Main8 {

    private static volatile long _longVal = 0;

    private static class LoopVolatile implements Runnable {
        @Override
        public void run() {
            long val = 0;
            while (val < 10000000L) {
                _longVal=val;
                val++;
            }
        }
    }

    private static class LoopVolatile2 implements Runnable {
        @Override
        public void run() {
            long val = 0;
            while (val < 10000000L) {
                _longVal=val;
                val++;
            }
        }
    }

    private static void testVolatile(){
        Thread t1 = new Thread(new LoopVolatile());
        t1.start();

        Thread t2 = new Thread(new LoopVolatile2());
        t2.start();

        while (t1.isAlive() || t2.isAlive()) {
        }

        System.out.println("final val is: " + _longVal);
    }

    public static void main(String[] args) {
        testVolatile();
    }
}
