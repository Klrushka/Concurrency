package task26;

import java.time.chrono.ThaiBuddhistChronology;

public class BusBoy implements Runnable {
    private Restaurant restaurant;
    public BusBoy(Restaurant restaurant){
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                synchronized (this){
                    while (restaurant.waitPerson.isCleanTable()){
                        wait();
                    }
                    System.out.println("Clean");
                    restaurant.waitPerson.setCleanTable(true);
                }
            }
        } catch (InterruptedException e){
            System.out.println("End");;
        }
    }
}
