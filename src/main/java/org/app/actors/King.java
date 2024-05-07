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
    private static final double PARTY_COST = 500.0; // Define the cost of the party
    private Transporter transporter; // Add a reference to the Transporter

    public King(TreasureRoomDoor treasureRoomDoor, Transporter transporter) {
        this.treasureRoomDoor = treasureRoomDoor;
        this.transporter = transporter; // Initialize the Transporter
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Random value to decide if the king can throw a party
                double targetValue = random.nextInt(50) + 25;
                checkForParty(targetValue);
                Thread.sleep(random.nextInt(5000) + 1000); // Sleep for a random time between 1 and 6 seconds
            } catch (InterruptedException e) {
                Logger.getInstance().log("King was interrupted: " + e.getMessage());
                return;  // Properly handle thread interruption
            }
        }
    }


    private void checkForParty(double targetValue) {
        Random random = new Random();
        int randomNum = random.nextInt(10); // Generate a random number between 0 and 9

        // The King will party if the random number is less than 7, making the party frequent
        canKingParty = transporter.getTransferCount() >= 1 && randomNum < 7;

        Logger.getInstance().log("Transporter transfer count: " + transporter.getTransferCount());
        if (canKingParty) {
            double accumulatedValue = PARTY_COST; // Subtract the cost of the party
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