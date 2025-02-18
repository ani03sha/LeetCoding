package org.redquark.leetcoding.design;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class BoundedBlockingQueue {

    // Semaphores for enqueue and dequeue
    private final Semaphore enqueueSemaphore;
    private final Semaphore dequeueSemaphore;
    // Queue to store elements
    private final Queue<Integer> elements;

    public BoundedBlockingQueue(int capacity) {
        this.enqueueSemaphore = new Semaphore(capacity);
        this.dequeueSemaphore = new Semaphore(0);
        this.elements = new LinkedList<>();
    }

    public void enqueue(int element) throws InterruptedException {
        // Acquire enqueue semaphore
        this.enqueueSemaphore.acquire();
        // Add the element to the queue
        this.elements.offer(element);
        // Release dequeue semaphore
        this.dequeueSemaphore.release();
    }

    public int dequeue() throws InterruptedException {
        // Acquire dequeue semaphore
        this.dequeueSemaphore.acquire();
        // Remove element from the queue
        final int element = this.elements.remove();
        // Release enqueue semaphore
        this.enqueueSemaphore.release();
        return element;
    }

    public int size() {
        return this.elements.size();
    }

    public static void main(String[] args) {
        final BoundedBlockingQueue queue = new BoundedBlockingQueue(5); // Capacity is 5

        // Producer thread
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    System.out.println("Producing: " + i);
                    queue.enqueue(i);
                    Thread.sleep(500); // Simulate production time
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    int item = queue.dequeue();
                    System.out.println("Consuming: " + item);
                    Thread.sleep(1000); // Simulate consumption time
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
