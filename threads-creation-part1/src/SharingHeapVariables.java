/*
 * Copyright (c) 2024. Michael Pogrebinsky - Top Developer Academy
 * https://topdeveloperacademy.com
 * All rights reserved
 */

public class SharingHeapVariables {
    public static void main(String[] args) throws InterruptedException {
        InventoryCounter inventoryCounter = new InventoryCounter();
        IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

        incrementingThread.start();
        decrementingThread.start();

        incrementingThread.join();
        decrementingThread.join();
        System.out.println(inventoryCounter.getItems());

    }

    public static class IncrementingThread extends Thread {
        private InventoryCounter inventoryCounter;

        public IncrementingThread(InventoryCounter intInventoryCounter){
            this.inventoryCounter   = intInventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i <  10000; i++) {
                inventoryCounter.increment();
            }
        }
    }

    public static class DecrementingThread extends Thread {
        private InventoryCounter inventoryCounter;

        public DecrementingThread(InventoryCounter intInventoryCounter){
            this.inventoryCounter   = intInventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i <  10000; i++) {
                inventoryCounter.decrement();
            }
        }
    }

    public static class InventoryCounter {
        private int items =0;

        public synchronized void increment(){
            items++;
        }
        public synchronized void decrement(){
            items--;
        }
        public synchronized int getItems(){
            return items;
        }
    }


}
