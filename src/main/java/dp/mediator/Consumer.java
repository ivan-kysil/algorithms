package dp.mediator;


public class Consumer extends Thread {
    private Mediator mediator;
    private static volatile int idx = 1;
    private String name;

    public Consumer(Mediator mediator) {
        this.mediator = mediator;
        name = "C-" + idx++;
        setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(name + ": " + mediator.getEvent().getMessage());
        }
    }
}
