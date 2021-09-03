package task35;

import java.util.concurrent.ArrayBlockingQueue;

public class WebCustomerLine extends  ArrayBlockingQueue<WebCustomer> {
    public WebCustomerLine(int maxLineSize) {
        super(maxLineSize);
    }
    public String toString() {
        if(this.size() == 0)
            return "[Empty]";
        StringBuilder result = new StringBuilder();
        for(WebCustomer customer : this)
            result.append(customer);
        return result.toString();
    }
}
