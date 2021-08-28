package task6;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class SleepingTask implements Runnable {
    private int rand = new Random().nextInt(10);
    @Override
    public void run()  {
        try {
            TimeUnit.SECONDS.sleep(rand);
            System.out.println("I'm sleeping " + rand);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
