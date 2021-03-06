package task2;

import java.util.concurrent.Callable;

public class Fibonacci implements Runnable, Callable<String> {
    private static int counter = 0;
    private final int id = counter;
    private final int howMach;

    public Fibonacci(int howMach){
        this.howMach = howMach;
        counter++;
    }

    private static int fib(int n) {
        if (n < 2) return 1;
        return fib(n - 2) + fib(n - 1);
    }

    @Override
    public void run() {
        for (int i = 0; i < howMach; i++) {
            System.out.println("#" + id + " " + fib(i + 1));
        }
    }

    @Override
    public String call() throws Exception {
        int sum = 0;
        for (int i = 0; i < howMach; i++){
            sum += fib(i);
        }
        return "#" + id + " " + sum;
    }
}
