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

    public Transporter(Deposit deposit, TreasureRoomDoor treasureRoom) {
        this.deposit = deposit;
        this.treasureRoom = treasureRoom;
        transportBag = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                while (deposit.size() < 10) { // Wait until there are 10 items in the deposit
                    Thread.sleep(1000); // Sleep for 1 second
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
                    treasureRoom.acquireWrite();
                    try {
                        for (Valuable v : transportBag) {
                            treasureRoom.add(v);
                            Logger.getInstance().log("Transporter Delivered to Treasure Room: " + v.getName());
                        }
                    } catch (Exception e) {
                        Logger.getInstance().log("Exception in Transporter while delivering: " + e.getMessage());
                    } finally {
                        treasureRoom.releaseWrite();
                        transportBag.clear();
                    }
                }
                Thread.sleep(3000); // Wait before next transport
            }
        } catch (InterruptedException e) {
            Logger.getInstance().log("Transporter interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}