package org.app;

import org.app.rooms.Deposit;
import org.app.actors.Transporter;
import org.app.actors.GuardsMan;
import org.app.rooms.TreasureRoomDoor;
import org.app.actors.Accountant;
import org.app.actors.King;
import org.app.actors.Miner;
import org.app.rooms.Mine;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Deposit deposit = new Deposit();
        Mine mine = new Mine();
        TreasureRoomDoor actualTreasureRoom = new org.app.rooms.TreasureRoom();
        TreasureRoomDoor treasureRoomDoor = new GuardsMan(actualTreasureRoom); // Decorate with GuardsMan
        Transporter transporter = new Transporter(deposit, treasureRoomDoor); // Create the Transporter
        King theKing = new King(treasureRoomDoor, transporter); // Pass the Transporter to the King
        Accountant accountant = new Accountant(treasureRoomDoor);

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 3; i++) {  // Three miners
            Thread minerThread = new Thread(new Miner(mine, deposit), "Miner-" + i);
            minerThread.start();
            threads.add(minerThread);
        }

        Thread transporterThread = new Thread(transporter, "Transporter");
        transporterThread.start();
        threads.add(transporterThread);

        Thread kingThread = new Thread(theKing, "King");
        kingThread.start();
        threads.add(kingThread);

        Thread accountantThread = new Thread(accountant, "Accountant");
        accountantThread.start();
        threads.add(accountantThread);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            for (Thread thread : threads) {
                thread.interrupt();
            }
        }));

        Thread.sleep(30000); // Sleep for 10 seconds

        System.out.println("Total deposited value: " + deposit.getTotalDepositedValue());
    }
}