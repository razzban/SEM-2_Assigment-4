package org.app.actors;

import org.app.rooms.Deposit;
import org.app.rooms.TreasureRoomDoor;
import org.app.logger.Logger;
import org.app.valuables.Valuable;

import java.util.ArrayList;
import java.util.List;

public class Transporter implements Runnable {
    private Deposit deposit;
    private List<Valuable> transportBag;
    private TreasureRoomDoor treasureRoom;
    private int transferCount = 0; // Add a counter for the number of transfers

    public Transporter(Deposit deposit, TreasureRoomDoor treasureRoom) {
        this.deposit = deposit;
        this.treasureRoom = treasureRoom;
        transportBag = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                while (deposit.size() < 3) { // Wait until there are 3 items in the deposit
                    Thread.sleep(2000); // Sleep for 2 seconds
                }
                while (!deposit.isEmpty() && !Thread.currentThread().isInterrupted()) {
                    try {
                        Valuable valuable = deposit.remove();
                        if (valuable != null) {
                            transportBag.add(valuable);
                            Logger.getInstance().log("Transporter Picked: " + valuable.getName() + " worth " + valuable.getValue());
                        }
                    } catch (Exception e) {
                        Logger.getInstance().log("Exception in Transporter while picking: " + e.getMessage());
                        break;
                    }
                }
                if (!transportBag.isEmpty()) {
                    Logger.getInstance().log("Transporter trying to acquire write access...");
                    treasureRoom.acquireWrite();
                    try {
                        Logger.getInstance().log("Transporter acquired write access.");
                        for (Valuable v : transportBag) {
                            treasureRoom.add(v);
                            Logger.getInstance().log("Transporter Delivered to Treasure Room: " + v.getName());
                        }
                        transferCount++; // Increment the counter each time valuables are transferred
                        Logger.getInstance().log("Transporter transfer count: " + transferCount);
                    } catch (Exception e) {
                        Logger.getInstance().log("Exception in Transporter while delivering: " + e.getMessage());
                    } finally {
                        treasureRoom.releaseWrite();
                        Logger.getInstance().log("Transporter released write access.");
                        transportBag.clear();
                    }
                }
                Thread.sleep(4000); // Wait for 4 seconds before next transport
            }
        } catch (InterruptedException e) {
            Logger.getInstance().log("Transporter interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    // Add a method to get the current count of transfers
    public int getTransferCount() {
        return transferCount;
    }
}