/*
 * Copyright (c) 2024. Michael Pogrebinsky - Top Developer Academy
 * https://topdeveloperacademy.com
 * All rights reserved
 */

import java.util.ArrayList;
import java.util.List;

public class TestVolatilyPrimiiveMembers {

    public static void main(String[] args) {

        MinMaxMetrics minMaxMetrics = new MinMaxMetrics();

        List<Thread> threads = new ArrayList<>();

        var value1 = new Thread(() -> {
            minMaxMetrics.addSample(1);
            System.out.println("Thread " + Thread.currentThread().getName() + " - Max value: " + minMaxMetrics.getMax());
            System.out.println("Thread " + Thread.currentThread().getName() + " - Min value: " + minMaxMetrics.getMin());
        }, "add 1");

        var value101 = new Thread(() -> {
            minMaxMetrics.addSample(101);
            System.out.println("Thread " + Thread.currentThread().getName() + " - Max value: " + minMaxMetrics.getMax());
            System.out.println("Thread " + Thread.currentThread().getName() + " - Min value: " + minMaxMetrics.getMin());

        }, "value 101");

        var value800 = new Thread(() -> {
            minMaxMetrics.addSample(800);
            System.out.println("Thread " + Thread.currentThread().getName() + " - Max value: " + minMaxMetrics.getMax());
            System.out.println("Thread " + Thread.currentThread().getName() + " - Min value: " + minMaxMetrics.getMin());
        }, "value 800");
        var valueMinus1 = new Thread(() -> {
            minMaxMetrics.addSample(-1);
            System.out.println("Thread " + Thread.currentThread().getName() + " - Max value: " + minMaxMetrics.getMax());
            System.out.println("Thread " + Thread.currentThread().getName() + " - Min value: " + minMaxMetrics.getMin());
        }, "value -1");

        threads.add(value1);
        threads.add(value101);
        threads.add(value800);
        threads.add(valueMinus1);

        threads.forEach( x -> {
            x.start();
            System.out.println("Running thread :" + x.getName());
        });

        threads.forEach( x -> {
            try {
                x.join();
            } catch (InterruptedException e) {

            }
        });
    }

    public static class MinMaxMetrics {
        // Member variables to track the min and max values
        private volatile long min;
        private volatile long max;

        /**
         * Initializes all member variables.
         * Set initial values for min and max.
         */
        public MinMaxMetrics() {
            // Initialize min to the maximum possible value and max to the minimum possible value
            this.min = Long.MAX_VALUE;
            this.max = Long.MIN_VALUE;
        }

        /**
         * Adds a new sample to our metrics.
         */
        public synchronized void addSample(long newSample) {
            synchronized (this){
                this.min = Math.min(newSample, this.min);
                this.max = Math.max(newSample, this.max);
            }
        }

        /**
         * Returns the smallest sample we've seen so far.
         */
        public long getMin() {
            return this.min;
        }

        /**
         * Returns the biggest sample we've seen so far.
         */
        public long getMax() {
            return this.max;
        }
    }
}
