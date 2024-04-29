package org.app.accountant;

import org.app.door.TreasureRoomDoor;
import org.app.logger.Logger;
import org.app.valuables.Valuable;

import java.util.List;

public class Accountant implements Runnable {
    private TreasureRoomDoor treasureRoomDoor;
    private volatile boolean running = true; // Flag to control the running of the thread

    public Accountant(TreasureRoomDoor treasureRoomDoor) {
        this.treasureRoomDoor = treasureRoomDoor;
    }

    @Override
    public void run() {
        while (running) {
            try {
                treasureRoomDoor.acquireRead();
                try {
                    List<Valuable> valuables = treasureRoomDoor.readValuables(); // Assuming this returns a List
                    logValuables(valuables); // Log the details of the valuables
                } finally {
                    treasureRoomDoor.releaseRead();
                }
                Thread.sleep(10000); // Sleep for 10 seconds
            } catch (InterruptedException e) {
                Logger.getInstance().log("Accountant thread interrupted. Stopping...");
                running = false; // Set running  false to exit the loop
                Thread.currentThread().interrupt(); // Re-interrupt the thread to restore the interrupt status
            }
        }
    }

    private void logValuables(List<Valuable> valuables) {
        Logger logger = Logger.getInstance();
        logger.log("Accountant reading valuables:");
        for (Valuable valuable : valuables) {
            logger.log(valuable.getName() + " valued at " + valuable.getValue());
        }
    }
}
