package org.app;

import org.app.rooms.Deposit;
import org.app.actors.Transporter;
import org.app.actors.GuardsMan;
import org.app.rooms.TreasureRoomDoor;
import org.app.actors.Accountant;
import org.app.actors.King;
import org.app.actors.Miner;
import org.app.rooms.Mine;
import org.app.rooms.Room; // Assuming this is the concrete implementation

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Deposit deposit = new Deposit();
        Mine mine = new Mine();
        TreasureRoomDoor actualTreasureRoom = new Room();
        TreasureRoomDoor treasureRoomDoor = new GuardsMan(actualTreasureRoom); // Decorate with GuardsMan
        King theKing = new King(treasureRoomDoor);
        Accountant accountant = new Accountant(treasureRoomDoor);

        for (int i = 0; i < 3; i++) {  // Three miners
            new Thread(new Miner(mine, deposit), "Miner-" + i).start();
        }
        new Thread(new Transporter(deposit, treasureRoomDoor), "Transporter").start();

        new Thread(theKing, "King").start();
        new Thread(accountant, "Accountant").start();

        Thread.sleep(10000); // Sleep for 10 seconds
    }
}

