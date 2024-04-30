package org.app.actors;
import org.app.rooms.Deposit;
import org.app.rooms.Mine;
import org.app.valuables.Valuable;

import org.app.logger.Logger;

public class Miner implements Runnable {
    private Mine mine;
    private Deposit deposit;

    public Miner(Mine mine, Deposit deposit) {
        this.mine = mine;
        this.deposit = deposit;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Valuable valuable = mine.getValuable();
                if (valuable != null) {
                    deposit.add(valuable);
                    Logger.getInstance().log("Mined and deposited in deposit box: " + valuable.getName() + " worth " + valuable.getValue());
                } else {
                    Logger.getInstance().log("No valuable found or mine exhausted.");
                }
                Thread.sleep(1000);  // Configurable delay
            } catch (Exception e) { // Catching a general exception to handle unexpected issues.
                Logger.getInstance().log("Error in mining process: " + e.getMessage());
                // Optionally sleep or handle the specific error if needed
            }
        }
    }
}

