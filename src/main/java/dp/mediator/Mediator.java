package dp.mediator;

public class Mediator {

    private Event storage;

    synchronized public void putEvent(final Event event) {
        while (storage != null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage = event;
        notifyAll();
    }

    synchronized public Event getEvent() {
        while (storage == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        final Event event = storage;
        storage = null;
        notifyAll();
        return event;
    }

}
