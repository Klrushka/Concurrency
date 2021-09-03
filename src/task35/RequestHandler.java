package task35;

import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class RequestHandler implements Runnable, Comparable<RequestHandler> {
    private static int counter = 0;
    private final int id = counter++;
    // Customers served during this shift:
    private int customersServed = 0;
    private WebCustomerLine customers;
    private boolean servingCustomerLine = true;

    public RequestHandler(WebCustomerLine cq) {
        customers = cq;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                WebCustomer customer = customers.take();
                TimeUnit.MILLISECONDS.sleep(
                        customer.getServiceTime());
                synchronized (this) {
                    customersServed++;
                    while (!servingCustomerLine)
                        wait();
                }
            }
        } catch (InterruptedException e) {
            System.out.println(this + "interrupted");
        }
        System.out.println(this + "terminating");
    }

    public synchronized void doSomethingElse() {
        customersServed = 0;
        servingCustomerLine = false;
    }

    public synchronized void serveCustomerLine() {
        assert !servingCustomerLine : "already serving: " + this;
        servingCustomerLine = true;
        notifyAll();
    }

    public String toString() {
        return "RequestHandler " + id + " ";
    }

    public String shortString() {
        return "RH" + id;
    }

    // Used by priority queue:
    public synchronized int compareTo(RequestHandler other) {
        return customersServed < other.customersServed ? -1 :
                (customersServed == other.customersServed ? 0 : 1);
    }

}
