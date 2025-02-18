package org.redquark.leetcoding.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class BuildingH2O {

    static class H2O {

        // Semaphore for the hydrogen thread - we need 2H for every O
        private final Semaphore hydrogenSemaphore;
        // Semaphore for the oxygen thread - as it needs to wait for 2H
        private final Semaphore oxygenSemaphore;

        public H2O() {
            this.hydrogenSemaphore = new Semaphore(2);
            this.oxygenSemaphore = new Semaphore(0);
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            this.hydrogenSemaphore.acquire();
            releaseHydrogen.run();
            this.oxygenSemaphore.release();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            this.oxygenSemaphore.acquire(2);
            releaseOxygen.run();
            this.hydrogenSemaphore.release(2);
        }
    }

    public static void main(String[] args) {
        final H2O h2o = new H2O();

        // Create an executor with fixed thread pool
        try (ExecutorService executor = Executors.newFixedThreadPool(10)) {

            // Simulating multiple H and O calls randomly
            Runnable releaseHydrogen = () -> System.out.print("H");
            Runnable releaseOxygen = () -> System.out.print("O");

            // Add multiple tasks for hydrogen and oxygen
            for (int i = 0; i < 5; i++) {
                executor.execute(() -> {
                    try {
                        h2o.hydrogen(releaseHydrogen);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }

            for (int i = 0; i < 3; i++) {
                executor.execute(() -> {
                    try {
                        h2o.oxygen(releaseOxygen);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }

            // Shutdown the executor
            executor.shutdown();
        }
    }
}
