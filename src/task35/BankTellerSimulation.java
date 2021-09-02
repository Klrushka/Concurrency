package task35;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BankTellerSimulation {
    static final int MAX_LINE_SIZE = 50;
    static final int ADJUSTMENT_PERIOD = 1000;

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
// If line is too long, customers will leave:
        CustomerLine customers =
                new CustomerLine(MAX_LINE_SIZE);
        exec.execute(new CustomerGenerator(customers));
// Manager will add and remove tellers as necessary:
        exec.execute(new TellerManager(
                exec, customers, ADJUSTMENT_PERIOD));
        if (args.length > 0) // Optional argument
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
        else {
            System.out.println("Press ‘Enter’ to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}
