package task26;

import static task26.Print.print;

public class WaitPerson implements Runnable {
    private boolean cleanTable = true;
    private Restaurant restaurant;
    public WaitPerson(Restaurant r) { restaurant = r; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized(this) {
                    while(restaurant.meal == null)
                        wait(); // ... for the chef to produce a meal
                }
                print("Waitperson got " + restaurant.meal);
                synchronized(restaurant.chef) {
                    cleanTable = false;
                    restaurant.meal = null;
                    restaurant.chef.notifyAll(); // Ready for another
                }
            }
        } catch(InterruptedException e) {
            print("WaitPerson interrupted");
        }
    }

    public boolean isCleanTable() {
        return cleanTable;
    }

    public void setCleanTable(boolean cleanTable) {
        this.cleanTable = cleanTable;
    }
}
