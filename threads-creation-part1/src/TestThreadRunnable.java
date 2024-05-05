/*
 * Copyright (c) 2024. Michael Pogrebinsky - Top Developer Academy
 * https://topdeveloperacademy.com
 * All rights reserved
 */

import javax.sound.midi.Soundbank;

public class TestThreadRunnable {
    public static void main(String[] args) {
        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Test new thread started");
                    }
                }
        );

        System.out.println("Before starting");
        thread.start();
        System.out.println("After starting");

    }



}
