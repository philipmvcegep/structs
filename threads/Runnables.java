package threads;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

// 1. Runnable : définit une tâche indépendamment du thread.
// Interface historique, mais toujours utilisée avec ExecutorService,
// les planificateurs et les threads virtuels.
class MyTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Exécution sur " +
            Thread.currentThread().getName());
    }
}

// 2. Runnable comme tâche périodique (type Cron Job)
class ScheduledJob implements Runnable {
    @Override
    public void run() {
        System.out.println("Récupération des cours boursiers...");
    }
}

// 3. Un même Runnable partagé entre deux threads
class SharedTask implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(
                Thread.currentThread().getName() + " : " + i);
        }
    }
}

// 4. Heap vs Stack : partage des données
class CounterTask implements Runnable {
    private int sharedCounter = 0; // Heap : partagé entre les threads

    @Override
    public void run() {
        int localCounter = 0; // Stack : propre à chaque exécution

        for (int i = 0; i < 1000; i++) {
            localCounter++;
            sharedCounter++; // Condition de concurrence !
        }

        System.out.println(
            Thread.currentThread().getName()
            + " local=" + localCounter
            + " partagé=" + sharedCounter);
    }
}

// 5. Version sécurisée avec AtomicInteger
class SafeCounterTask implements Runnable {
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.incrementAndGet();
        }
    }

    public int getCounter() {
        return counter.get();
    }
}

public class Runnables {
    public static void main(String[] args) throws Exception {

        // EXEMPLE 1 : Runnable de base
        Runnable task = new MyTask();
        new Thread(task).start();

        // EXEMPLE 2 : Runnable planifié périodiquement
        ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(
            new ScheduledJob(), 0, 5, TimeUnit.SECONDS
        );

        // EXEMPLE 3 : Plusieurs Runnable sur UN SEUL thread
        Runnable task1 = () -> System.out.println("Tâche 1");
        Runnable task2 = () -> System.out.println("Tâche 2");

        new Thread(() -> {
            task1.run();
            task2.run();
        }).start();

        // EXEMPLE 4 : DEUX threads partagent UN SEUL Runnable
        Runnable sharedTask = new SharedTask();

        new Thread(sharedTask, "Thread-A").start();
        new Thread(sharedTask, "Thread-B").start();

        // EXEMPLE 5 : Heap vs Stack et condition de concurrence
        CounterTask counter = new CounterTask();

        Thread t1 = new Thread(counter, "Thread-1");
        Thread t2 = new Thread(counter, "Thread-2");

        t1.start();
        t2.start();

   

        // Solution sécurisée : AtomicInteger
        SafeCounterTask safeCounter = new SafeCounterTask();

        Thread t3 = new Thread(safeCounter);
        Thread t4 = new Thread(safeCounter);

        t3.start();
        t4.start();

        t3.join();
        t4.join();

        System.out.println("Compteur sécurisé : "
            + safeCounter.getCounter()); // Toujours 2000

        scheduler.shutdown();
    }
}
