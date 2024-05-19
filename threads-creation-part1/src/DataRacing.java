/*
 * Copyright (c) 2024. Michael Pogrebinsky - Top Developer Academy
 * https://topdeveloperacademy.com
 * All rights reserved
 */

public class DataRacing {
    public static void main(String[] args) {
        SharedClass sharedClass = new SharedClass();

        Thread thread1 = new Thread(()-> {
            for(int i=0; i < Integer.MAX_VALUE; i++){
                sharedClass.inrement();
            }
        });

        Thread thread2 = new Thread(()-> {
            for(int i=0; i < Integer.MAX_VALUE; i++){
                sharedClass.checkDataRacing();
            }
        });

        thread1.start();
        thread2.start();
    }


    public static class SharedClass{
        private int x =0;
        private int y =0;

        public void inrement(){
            x++;
            y++;
        }

        public void checkDataRacing(){
            if(x> y){
                System.out.println("x > y - DataRacing Detected");
            }
        }

    }

}
