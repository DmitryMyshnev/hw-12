package Task2;

@Repeat(3)
public class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Hallo!");
    }
}
