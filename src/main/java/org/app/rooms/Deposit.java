package org.app.rooms;

import org.app.logger.Logger;
import org.app.valuables.Valuable;
import org.app.valuables.WoodenCoin;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Deposit {
    private final BlockingQueue<Valuable> queue;
    private final Logger logger;
    private int totalDepositedValue = 0;

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
            totalDepositedValue += valuable.getValue(); // assuming Valuable has a getValue() method
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

    public int getTotalDepositedValue() {
        return totalDepositedValue;
    }

    public int size() {
        return queue.size();
    }
}