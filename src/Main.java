import java.util.concurrent.*;

public class Main {
    static int atomCountHydrogen = 0;
    static int phase;
    static boolean finish = false;
    static Phaser phaser = new Phaser(4);

    public static void main(String[] args) {
        ExecutorService oxygen = Executors.newSingleThreadExecutor();
        ExecutorService hydrogen = Executors.newSingleThreadExecutor();
        phase = phaser.getPhase();
        oxygen.execute(() -> {
            releaseOxygen();
        });
        hydrogen.execute(() -> {
            releaseHydrogen();
        });
        for (; ; ) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (phaser.getArrivedParties() == 3) {
                System.out.print(", ");
                phaser.arrive();
                if (phase > 5) {
                    finish = true;
                    break;
                }
            }
            phase = phaser.getPhase();
        }
        oxygen.shutdown();
        hydrogen.shutdown();
    }

    static void releaseOxygen() {
        for (; ; ) {
            if (finish) {
                break;
            }
            System.out.print("O");
            phaser.arriveAndAwaitAdvance();
        }
    }

    static void releaseHydrogen() {
        for (; ; ) {
            atomCountHydrogen++;
            if (finish) {
                break;
            }
            if (atomCountHydrogen == 2) {
                System.out.print("H");
                atomCountHydrogen = 0;
                phaser.arriveAndAwaitAdvance();
            } else {
                System.out.print("H");
                phaser.arrive();
            }
        }
    }
}
