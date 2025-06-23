package org.redquark.extras.solutions.design;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaskScheduler {

    // Queue to store all tasks
    private final Queue<ScheduledTask> tasks;
    private final Lock lock;
    private final Condition newTask;
    private final ExecutorService workerPool;
    private final Thread scheduledThread;
    private volatile boolean isShutdown;

    public TaskScheduler(int threadPoolSize) {
        this.tasks = new PriorityQueue<>();
        this.lock = new ReentrantLock();
        this.newTask = this.lock.newCondition();
        this.workerPool = Executors.newFixedThreadPool(threadPoolSize);
        this.scheduledThread = new Thread(() -> {
            try {
                runSchedulerLoop();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        this.scheduledThread.start();
        this.isShutdown = false;
    }

    public void schedule(Runnable task, long delayMillis) {
        this.lock.lock();
        try {
            ScheduledTask scheduledTask = new ScheduledTask(delayMillis, task);
            this.tasks.offer(scheduledTask);
            this.newTask.signal();
        } finally {
            this.lock.unlock();
        }
    }

    public void shutdown() {
        this.isShutdown = true;
        this.scheduledThread.interrupt();
        this.workerPool.shutdown();
    }

    private void runSchedulerLoop() throws InterruptedException {
        while (!this.isShutdown) {
            this.lock.lock();
            try {
                while (this.tasks.isEmpty()) {
                    newTask.await();
                }
                final long currentTime = System.currentTimeMillis();
                final ScheduledTask nextTask = this.tasks.peek();
                if (nextTask.time <= currentTime) {
                    this.tasks.remove();
                    workerPool.execute(nextTask.task);
                } else {
                    final long waitTime = nextTask.time - currentTime;
                    newTask.await(waitTime, TimeUnit.MILLISECONDS);
                }
            } finally {
                lock.unlock();
            }
        }
    }

    static class ScheduledTask implements Comparable<ScheduledTask> {

        final long time;
        final Runnable task;

        ScheduledTask(long delayMillis, Runnable task) {
            this.time = System.currentTimeMillis() + delayMillis;
            this.task = task;
        }

        @Override
        public int compareTo(ScheduledTask other) {
            return Long.compare(this.time, other.time);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TaskScheduler scheduler = new TaskScheduler(3);

        scheduler.schedule(() -> print("Task 1, delay 1s"), 1000);
        scheduler.schedule(() -> print("Task 2, delay 500ms"), 500);
        scheduler.schedule(() -> print("Task 3, delay 2s"), 2000);
        scheduler.schedule(() -> print("Task 4, delay 1500ms"), 1500);
        scheduler.schedule(() -> print("Task 5, delay 0ms"), 0);

        // Let all tasks finish
        Thread.sleep(3000);
        scheduler.shutdown();
    }

    private static void print(String msg) {
        System.out.println("[ " + System.currentTimeMillis() % 100000 + " ] " + msg);
    }
}
