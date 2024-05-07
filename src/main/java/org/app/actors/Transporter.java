package org.app.actors;
import org.app.rooms.Deposit;
import org.app.rooms.TreasureRoomDoor;
import org.app.logger.Logger;
import org.app.valuables.Valuable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Transporter implements Runnable {
    private Deposit deposit;
    private Random randomGenerator = new Random();
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
                double random = randomGenerator.nextInt(150) + 50;  // Target value to collect
                double value = 0;
                while (value < random && !Thread.currentThread().isInterrupted()) {
                    try {
                        Valuable valuable = deposit.remove();  // Assuming remove is blocking/waiting
                        if (valuable != null) {
                            transportBag.add(valuable);
                            value += valuable.getValue();
                            Logger.getInstance().log(" Transporter Picked: " + valuable.getName() + " worth " + valuable.getValue());
                        }
                    } catch (Exception e) {
                        Logger.getInstance().log(e.getMessage());
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