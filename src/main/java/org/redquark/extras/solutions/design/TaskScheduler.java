package org.redquark.extras.solutions.design;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaskScheduler {

    // Min heap to store all tasks based on their time
    private final Queue<ScheduledTask> taskQueue;
    // Lock for thread safety
    private final Lock lock;
    // Condition that represents new task
    private final Condition newTask;
    // Thread Pool Executor
    private final ExecutorService workerPool;
    // Background thread to actually perform the task
    private final Thread schedulerThread;
    // Flag to indicate if the scheduler is down
    private volatile boolean isShutDown;

    public TaskScheduler(int threadPoolSize) {
        this.taskQueue = new PriorityQueue<>(Comparator.comparingLong(task -> task.time));
        this.lock = new ReentrantLock();
        this.newTask = this.lock.newCondition();
        this.workerPool = Executors.newFixedThreadPool(threadPoolSize);
        this.schedulerThread = new Thread(() -> {
            try {
                runSchedulerLoop();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        this.schedulerThread.start();
        this.isShutDown = false;
    }

    public void schedule(Runnable task, long delayMillis) {
        this.lock.lock();
        try {
            final ScheduledTask scheduledTask = new ScheduledTask(delayMillis, task);
            this.taskQueue.offer(scheduledTask);
            this.newTask.signal();
        } finally {
            this.lock.unlock();
        }
    }

    public void shutdown() {
        this.isShutDown = true;
        this.schedulerThread.interrupt();
        this.workerPool.shutdown();
    }

    private void runSchedulerLoop() throws InterruptedException {
        while (!this.isShutDown) {
            this.lock.lock();
            try {
                while (this.taskQueue.isEmpty()) {
                    this.newTask.await();
                }
                final long currentTime = System.currentTimeMillis();
                // Get the next task from the queue
                final ScheduledTask task = this.taskQueue.peek();
                if (task.time <= currentTime) {
                    this.taskQueue.remove();
                    this.workerPool.execute(task.task);
                } else {
                    final long waitTime = task.time - currentTime;
                    this.newTask.await(waitTime, TimeUnit.MILLISECONDS);
                }
            } finally {
                this.lock.unlock();
            }
        }
    }

    static class ScheduledTask {
        final long time;
        final Runnable task;

        ScheduledTask(long delayTimeMillis, Runnable task) {
            this.time = System.currentTimeMillis() + delayTimeMillis;
            this.task = task;
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
