package org.redquark.leetcoding.concurrency;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class PrintZeroEvenOdd {

    static class ZeroEvenOdd {

        private final int n;
        private final Semaphore zeroSemaphore;
        private final Semaphore evenSemaphore;
        private final Semaphore oddSemaphore;

        public ZeroEvenOdd(int n) {
            this.n = n;
            this.zeroSemaphore = new Semaphore(1);
            this.evenSemaphore = new Semaphore(0);
            this.oddSemaphore = new Semaphore(0);
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                this.zeroSemaphore.acquire();
                printNumber.accept(0);
                if (i % 2 == 0) {
                    this.evenSemaphore.release();
                } else {
                    this.oddSemaphore.release();
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for (int i = 2; i <= n; i += 2) {
                this.evenSemaphore.acquire();
                printNumber.accept(i);
                this.zeroSemaphore.release();
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i += 2) {
                this.oddSemaphore.acquire();
                printNumber.accept(i);
                this.zeroSemaphore.release();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int n = 5; // Change this to test different values

        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(n);
        IntConsumer printNumber = System.out::print; // Lambda to print numbers

        Thread zeroThread = new Thread(() -> {
            try {
                zeroEvenOdd.zero(printNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread evenThread = new Thread(() -> {
            try {
                zeroEvenOdd.even(printNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread oddThread = new Thread(() -> {
            try {
                zeroEvenOdd.odd(printNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Start all threads
        zeroThread.start();
        evenThread.start();
        oddThread.start();

        // Wait for all threads to complete
        try {
            zeroThread.join();
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
