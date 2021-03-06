import task1.TestClass;
import task2.Fibonacci;
import task26.Chef;
import task26.Meal;
import task26.Restaurant;
import task26.WaitPerson;
import task35.*;
import task5.CallFib;
import task6.SleepingTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

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


        for (int i = 0; i < 3; i++) {
            new Thread(new Fibonacci(10)).start();
        }

        System.out.println();

        /*
        Exercise 5: (2) Modify Exercise 2 so that the task is a Callable that sums the values of
        all the Fibonacci numbers. Create several tasks and display the results.
         */

        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> callFibs = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            callFibs.add(executorService.submit(new CallFib(i)));
        }

        for (Future<String> fs : callFibs) {
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

        for (int i = 0; i < times; i++) {
            executorService1.execute(new SleepingTask());
        }
        executorService1.shutdown();


        /*
        Exercise 26: (8) Add a BusBoy class to Restaurant.java. After the meal is delivered,
        the WaitPerson should notify the BusBoy to clean up.
         */


        new Restaurant();


        /*
        Exercise 31: (8) Change DeadlockingDiningPhilosophers.java so that when a
        philosopher is done with its chopsticks, it drops them into a bin. When a philosopher wants
        to eat, it takes the next two available chopsticks from the bin. Does this eliminate the
        possibility of deadlock? Can you reintroduce deadlock by simply reducing the number of
        available chopsticks?



        start in pack

        Can you reintroduce deadlock by simply reducing the number of
        available chopsticks?

        no


        Does this eliminate the possibility of deadlock?

        Yes, but if philosophers put sticks in the bin, we don't need 5 sticks
        */




        /*
        Exercise 35: (8) Modify BankTellerSimulation.java so that it represents Web clients
        making requests of a fixed number of servers. The goal is to determine the load that the
        group of servers can handle.

        start in WebStart
         */


    }
}
