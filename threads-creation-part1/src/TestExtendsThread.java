/*
 * Copyright (c) 2024. Michael Pogrebinsky - Top Developer Academy
 * https://topdeveloperacademy.com
 * All rights reserved
 */

public class TestExtendsThread {

    public static void main(String[] args) {
        TestThread testThread = new TestThread();
        System.out.println("Before start thread");
        testThread.start();
        System.out.println("After start thread");
    }


    private static class TestThread extends Thread {

        @Override
        public void start (){
            System.out.println("Calling thread extended");
            super.start();
        }

    }
}
