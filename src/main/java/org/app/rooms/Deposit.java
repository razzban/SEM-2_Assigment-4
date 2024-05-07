package org.app.rooms;

import org.app.logger.Logger;
import org.app.valuables.Valuable;
import org.app.valuables.WoodenCoin;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Deposit {
    private final BlockingQueue<Valuable> queue;
    private final Logger logger;

    public Deposit() {
        queue = new LinkedBlockingQueue<>();
        logger = Logger.getInstance();
    }

    public void add(Valuable valuable) throws InterruptedException {
        if (valuable instanceof WoodenCoin) {
            logger.logWoodenCoinDiscard();
        } else {
            queue.put(valuable);
            logger.logDeposit(valuable);
        }
    }

    public Valuable remove() throws InterruptedException {
        if (queue.isEmpty()) {
            logger.logEmptyRemoval();
            return null;
        }
        Valuable valuable = queue.take();
        logger.logRemoval(valuable);
        return valuable;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}