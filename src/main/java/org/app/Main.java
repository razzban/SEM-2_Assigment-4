package org.app;

import org.app.deposit.Deposit;
import org.app.valuableTransporter.Transporter;
import org.app.guardsMan.GuardsMan;
import org.app.door.TreasureRoomDoor;
import org.app.accountant.Accountant;
import org.app.king.King;
import org.app.miner.Miner;
import org.app.mine.Mine;
import org.app.treasureRoom.Room; // Assuming this is the concrete implementation

public class Main {
    public static void main(String[] args) {
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
    }
}
