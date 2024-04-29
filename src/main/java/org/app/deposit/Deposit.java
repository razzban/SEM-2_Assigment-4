package org.app.deposit;

import org.app.logger.Logger;
import org.app.valuables.Valuable;
import org.app.valuables.WoodenCoin;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Deposit {
    private final List<Valuable> list;
    private final Logger logger;
    private final Object lock = new Object();

    public Deposit() {
        // Ensuring thread safety with a synchronized list
        list = Collections.synchronizedList(new ArrayList<>());
        logger = Logger.getInstance();
    }

    public void add(Valuable valuable) {
        synchronized (lock) {
            if (valuable instanceof WoodenCoin) {
                logger.log("Wooden Coin detected, discarded.");
            } else {
                list.add(valuable);
                logger.log(valuable.getName() + " deposited.");
            }
        }
    }

    // Removes and returns the first Valuable
    public Valuable remove() {
        synchronized (list) {
            if (list.isEmpty()) {
                logger.log("Attempt to remove from an empty deposit.");
                return null;
            }
            Valuable valuable = list.remove(0);
            logger.log(valuable.getName() + " removed from the deposit.");
            return valuable;
        }
    }

    // Peeks at the first Valuable without removing
    public Valuable peek() {
        synchronized (list) {
            return list.isEmpty() ? null : list.get(0);
        }
    }

    // Returns the current size of the deposit
    public int size() {
        synchronized (list) {
            return list.size();
        }
    }
}
