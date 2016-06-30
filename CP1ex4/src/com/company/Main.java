package com.company;

import java.util.concurrent.Semaphore;

public class Main {

    Semaphore s1 = new Semaphore(0);
    Semaphore s2 = new Semaphore(0);
    Semaphore s3 = new Semaphore(0);
    Semaphore s4 = new Semaphore(0);


    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        new T4().start();
        new T2().start();
        new T5().start();
        new T3().start();
        new T1().start();
    }

    class T1 extends Thread{
        @Override
        public void run() {
            System.out.println("T1");
            s1.release();
            s1.release();
        }
    }
    class T2 extends Thread{
        @Override
        public void run() {
            System.out.println("T2");
            s2.release();
            s2.release();
        }
    }
    class T3 extends Thread{
        @Override
        public void run() {
            try {
                s1.acquire();
                s2.acquire();
                System.out.println("T3");
                s3.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class T4 extends Thread{
        @Override
        public void run() {
            try {
                s1.acquire();
                s2.acquire();
                System.out.println("T4");
                s4.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class T5 extends Thread{
        @Override
        public void run() {
            try {
                s3.acquire();
                s4.acquire();
                System.out.println("T5");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
