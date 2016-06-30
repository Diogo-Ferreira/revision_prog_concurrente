package com.company;

import java.util.concurrent.Semaphore;

public class Main {

    Semaphore s1 = new Semaphore(1);
    Semaphore s2 = new Semaphore(1);
    int out = 0;
    int n = 0;
    public static void main(String[] args) throws InterruptedException {
	    new Main();
    }

    public Main() throws InterruptedException {
        T3 t3 = new T3();
        T1 t1 = new T1();
        T2 t2 = new T2();
        t3.start();
        t1.start();
        t2.start();
        t3.join();
        t1.join();
        t2.join();
        System.out.println("FINAL : out = " + out + " n = " + n);
    }

    class T1 extends Thread{
        @Override
        public void run() {
            try {
                s1.acquire();
                s2.acquire();
                out++;
                n--;
                System.out.println("T1 : out = " + out + " n = " + n);
                s2.release();
                s1.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    class T2 extends Thread{
        @Override
        public void run() {
            try {
                s2.acquire();
                out --;
                System.out.println("T2 : out = " + out + " n = " + n);
                s2.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class T3 extends Thread{
        @Override
        public void run() {
            try {
                s1.acquire();
                n++;
                System.out.println("T3 : out = " + out + " n = " + n);
                s1.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
