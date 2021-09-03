package task35;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WebCustomerGenerator implements Runnable {
    private WebCustomerLine customers;
    private static Random rand = new Random(47);
    public WebCustomerGenerator(WebCustomerLine cq) {
        customers = cq;
    }
    public void run() {try {
        while(!Thread.interrupted()) {
            TimeUnit.MILLISECONDS.sleep(rand.nextInt(300));
            customers.put(new WebCustomer(rand.nextInt(1000)));
        }
    } catch(InterruptedException e) {
        System.out.println("CustomerGenerator interrupted");
    }
        System.out.println("CustomerGenerator terminating");
    }
}
