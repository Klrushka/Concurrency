package task2;

public class Fibonacci implements Runnable {
    private static int counter = 0;
    private final int id = counter;

    public Fibonacci() {
        counter++;
    }

    private static int fib(int n) {
        if (n < 2) return 1;
        return fib(n - 2) + fib(n - 1);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("#" + id + " " + fib(i + 1));
        }
    }
}
