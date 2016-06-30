package com.company;

import java.util.concurrent.Semaphore;

public class Main {

    Semaphore s1 = new Semaphore(1);
    Semaphore s2 = new Semaphore(1);
    Semaphore s3= new Semaphore(-1);
    Semaphore s4 = new Semaphore(1);

    public static void main(String[] args) {
	    new Main();
    }

    public Main() {
        new T1().start();
        new T2().start();
        new T3().start();
    }

    class T1 extends Thread{
        @Override
        public void run() {
            while (true) {
                try {
                    s2.acquire();
                    s1.acquire();
                    System.out.print("A1.");
                    s1.release();
                    s4.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class T2 extends Thread{
        @Override
        public void run() {
            while(true){
                try {
                    s4.acquire();
                    s1.acquire();
                    System.out.print("A2.");
                    s1.release();
                    s2.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    class T3 extends Thread{
        @Override
        public void run() {
            try {
                s3.acquire();
                System.out.println("A3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
