import task1.TestClass;
import task2.Fibonacci;

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
            new Thread(new Fibonacci()).start();
        }



        /*

         */








    }
}
