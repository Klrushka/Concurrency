package task35;

public class WebCustomer {
    private final int serviceTime;
    public WebCustomer(int tm) { serviceTime = tm; }
    public int getServiceTime() { return serviceTime; }
    public String toString() {
        return "[" + serviceTime + "]";
    }
}
