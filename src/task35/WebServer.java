package task35;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class WebServer implements Runnable{
    private ExecutorService exec;
    private WebCustomerLine customers;
    private PriorityQueue<RequestHandler> workingTellers =
            new PriorityQueue<>();
    private Queue<RequestHandler> tellersDoingOtherThings =
            new LinkedList<>();
    private int adjustmentPeriod;
    private int maxHandel;
    private static Random rand = new Random(47);

    public WebServer(ExecutorService e, WebCustomerLine customers, int adjustmentPeriod, int maxHandel) {
        exec = e;
        this.customers = customers;
        this.maxHandel = maxHandel;
        this.adjustmentPeriod = adjustmentPeriod;
// Start with a single teller:
        RequestHandler rh = new RequestHandler(customers);
        exec.execute(rh);
        workingTellers.add(rh);
    }

    public void adjustTellerNumber() {
// This is actually a control system. By adjusting
// the numbers, you can reveal stability issues in
// the control mechanism.
// If line is too long, add another teller:
        if (customers.size() / workingTellers.size() > 2) {
// If tellers are on break or doing
// another job, bring one back:
            if (tellersDoingOtherThings.size() > 0) {
                RequestHandler rh = tellersDoingOtherThings.remove();
                rh.serveCustomerLine();
                workingTellers.offer(rh);
                return;
            }
// Else create (hire) a new teller
            if (workingTellers.size() < maxHandel) {
                RequestHandler rh = new RequestHandler(customers);
                exec.execute(rh);
                workingTellers.add(rh);
                return;
            }
        }
// If line is short enough, remove a teller:
        if (workingTellers.size() > 1 && customers.size() / workingTellers.size() < 2)
            reassignOneTeller();
// If there is no line, we only need one teller:
        if (customers.size() == 0)
            while (workingTellers.size() > 1)
                reassignOneTeller();
    }

    // Give a teller a different job or a break:
    private void reassignOneTeller() {
        RequestHandler rh = workingTellers.poll();
        rh.doSomethingElse();
        tellersDoingOtherThings.offer(rh);
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjustTellerNumber();
                System.out.print(customers + " { ");
                for (RequestHandler rh : workingTellers)
                    System.out.print(rh.shortString() + " ");
                System.out.println("}");
            }
        } catch (InterruptedException e) {
            System.out.println(this + "interrupted");
        }
        System.out.println(this + "terminating");
    }

    public String toString() {
        return "RequestHandler ";
    }

}
