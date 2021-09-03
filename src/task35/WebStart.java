package task35;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WebStart {
    static final int MAX_LINE_SIZE = 50;
    static final int ADJUSTMENT_PERIOD = 1000;

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
// If line is too long, customers will leave:
        WebCustomerLine customers = new WebCustomerLine(MAX_LINE_SIZE);
        exec.execute(new WebCustomerGenerator(customers));
// Manager will add and remove tellers as necessary:
        exec.execute(new WebServer(exec, customers, ADJUSTMENT_PERIOD,20));

        if (args.length > 0) // Optional argument
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
        else {
            System.out.println("Press ‘Enter’ to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}
