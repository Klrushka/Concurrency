package task31;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class DeadlockingDiningPhilosophers {
    public static void main(String[] args) throws Exception {
        int ponder = 5;
        if (args.length > 0)
            ponder = Integer.parseInt(args[0]);
        int size = 5;
        if (args.length > 1)
            size = Integer.parseInt(args[1]);
        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] sticks = new Chopstick[size];
        LinkedBlockingDeque<Chopstick> bin = new LinkedBlockingDeque<>();
        for (int i = 0; i < size; i++) {
            sticks[i] = new Chopstick();
            bin.put(sticks[i]);
        }
        for (int i = 0; i < size; i++)
            exec.execute(new Philosopher(
                    sticks[i], sticks[(i + 1) % size], i, ponder, bin));
        if (args.length == 3 && args[2].equals("timeout"))
            TimeUnit.SECONDS.sleep(5);
        else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();

            exec.shutdownNow();

        }
    }
}
