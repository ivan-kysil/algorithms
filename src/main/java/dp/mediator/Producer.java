package dp.mediator;

import java.util.Random;

public class Producer extends Thread {
    private Mediator mediator;
    private static volatile int idx = 1;
    private static Random rnd = new Random();
    private String name;

    public Producer(Mediator mediator) {
        this.mediator = mediator;
        name = "P-" + idx++;
    }

    @Override
    public void run() {
        int i = 0;
        while (i++ < 1000) {
            final int delayMs = rnd.nextInt(10);
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mediator.putEvent(new Event(name + "(" + i + ")"));
        }
        System.out.println("Producer completed. " + name);
    }
}
