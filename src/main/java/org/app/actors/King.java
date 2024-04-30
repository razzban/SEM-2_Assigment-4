package org.app.actors;

import org.app.rooms.TreasureRoomDoor;
import org.app.logger.Logger;
import org.app.valuables.Valuable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class King implements Runnable {
    private Random random = new Random();
    private TreasureRoomDoor treasureRoomDoor;
    private boolean canKingParty = false;

    public King(TreasureRoomDoor treasureRoomDoor) {
        this.treasureRoomDoor = treasureRoomDoor;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Random value to decide if the king can throw a party
                double targetValue = random.nextInt(100) + 50;
                checkForParty(targetValue);
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Logger.getInstance().log("King was interrupted: " + e.getMessage());
                return;  // Properly handle thread interruption
            }
        }
    }

    private void checkForParty(double targetValue) {
        double accumulatedValue = 0;
        List<Valuable> temp = new ArrayList<>();

        treasureRoomDoor.acquireWrite();
        try {
            while (accumulatedValue < targetValue) {
                try {
                    Valuable valuable = treasureRoomDoor.retrieve();
                    accumulatedValue += valuable.getValue();
                    temp.add(valuable);
                } catch (Exception e) {
                    Logger.getInstance().log("No more valuables to retrieve: " + e.getMessage());
                    returnValuables(temp);
                    return;
                }
            }
            canKingParty = true;
        } finally {
            treasureRoomDoor.releaseWrite();
        }

        if (canKingParty) {
            Logger.getInstance().log("----PARTY IS HERE---THE KING IS HAPPY---");
        } else {
            Logger.getInstance().log("-----NOT ENOUGH RESOURCES--POOR KING");
        }
    }

    private void returnValuables(List<Valuable> valuables) {
        for (Valuable valuable : valuables) {
            treasureRoomDoor.add(valuable);
        }
    }
}
