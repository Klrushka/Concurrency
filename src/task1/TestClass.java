package task1;

public class TestClass implements Runnable{

    public TestClass(){
        System.out.println("Start ");
    }


    @Override
    public void run() {
        for (int i = 0; i < 5; i++){
            System.out.println("Message " + i);
            Thread.yield();
        }
        System.out.println("Finish");
    }


}
