import task1.TestClass;
import task2.Fibonacci;
import task5.CallFib;
import task6.SleepingTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {


        /*
        Exercise 1: (2) Implement a Runnable. Inside run( ), print a message, and then call
        yield( ). Repeat this three times, and then return from run( ). Put a startup message in the
        constructor and a shutdown message when the task terminates. Create a number of these
        tasks and drive them using threads.
         */

        TestClass testClass = new TestClass();

        testClass.run();


        /*
        Exercise 2: (2) Following the form of generics/Fibonacci.java, create a task that
        produces a sequence of n Fibonacci numbers, where n is provided to the constructor of the
        task. Create a number of these tasks and drive them using threads.
         */


        for (int i = 0; i < 3; i++){
            new Thread(new Fibonacci(10)).start();
        }

        System.out.println();

        /*
        Exercise 5: (2) Modify Exercise 2 so that the task is a Callable that sums the values of
        all the Fibonacci numbers. Create several tasks and display the results.
         */

        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> callFibs = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            callFibs.add(executorService.submit(new CallFib(i)));
        }

        for (Future<String> fs : callFibs){
            try {
                System.out.println(fs.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();



        /*
        Exercise 6: (2) Create a task that sleeps for a random amount of time between 1 and 10
        seconds, then displays its sleep time and exits. Create and run a quantity (given on the
        command line) of these tasks.
         */

        ExecutorService executorService1 = Executors.newCachedThreadPool();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter please, how many times we will wait...");
        int times = scanner.nextInt();

        for (int i = 0; i < times; i++){
            executorService1.execute(new SleepingTask());
        }
        executorService1.shutdown();














    }
}
