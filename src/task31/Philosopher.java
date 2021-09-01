package task31;

import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import static task26.Print.print;

public class Philosopher implements Runnable {
    private LinkedBlockingDeque<Chopstick> bin;
    private Chopstick left;
    private Chopstick right;
    private final int id;
    private final int ponderFactor;
    private Random rand = new Random(47);

    private void pause() throws InterruptedException {
        if (ponderFactor == 0) return;
        TimeUnit.MILLISECONDS.sleep(
                rand.nextInt(ponderFactor * 250));
    }

    public Philosopher(Chopstick left, Chopstick right,
                       int ident, int ponder, LinkedBlockingDeque<Chopstick> bin) {
        this.left = left;
        this.right = right;
        id = ident;
        ponderFactor = ponder;
        this.bin = bin;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                print(this + " " + "thinking");
                pause();
// Philosopher becomes hungry
                print(this + " " + "grabbing right");
                right = bin.take();
                print(this + " " + "grabbing left");
                left = bin.take();
                print(this + " " + "eating");
                pause();
                bin.put(right);
                bin.put(left);
            }
        } catch (InterruptedException e) {
            print(this + " " + "exiting via interrupt");
        }
    }

    public String toString() {
        return "Philosopher " + id;
    }
}
