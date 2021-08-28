package task5;

import task2.Fibonacci;


import java.util.concurrent.Callable;


public class CallFib implements Callable<String> {
    private static int counter = 0;
    private final int id = counter;
    private final int howMach;

    public CallFib (int howMach){
        this.howMach = howMach;
        counter++;
    }

    private static int fib(int n) {
        if (n < 2) return 1;
        return fib(n - 2) + fib(n - 1);
    }

    @Override
    public String call() throws Exception {
        int sum = 0;
        for (int i = 0; i < howMach; i++){
            sum += fib(i);
        }
        return "#" + id + " sum of " + howMach + " values = " + sum;
    }
}
