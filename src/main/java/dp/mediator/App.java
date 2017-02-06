package dp.mediator;

public class App {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Mediator mediator = new Mediator();
        Producer producer1 = new Producer(mediator);
        producer1.start();
        Producer producer2 = new Producer(mediator);
        producer2.start();
        Producer producer3 = new Producer(mediator);
        producer3.start();
        new Consumer(mediator).start();

        producer1.join();
        producer2.join();
        producer3.join();

        System.out.println("Time run:" + (System.currentTimeMillis() - start));
    }

}
