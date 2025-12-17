package L5Z1_Platformowka;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ScoreUpdater implements Runnable {
    private final AtomicInteger score;
    private final BlockingQueue<Integer> events;
    private boolean running;

    public ScoreUpdater(AtomicInteger score, BlockingQueue<Integer> events, boolean running) {
        this.score = score;
        this.events = events;
        this.running = running;
    }

    @Override
    public void run() {
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                int delta = events.take(); // czeka na zdarzenia
                score.addAndGet(delta);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
