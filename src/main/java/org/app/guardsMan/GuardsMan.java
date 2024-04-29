package org.app.guardsMan;

import org.app.valuables.Valuable;
import org.app.door.TreasureRoomDoor;
import java.util.logging.Logger;

public class GuardsMan implements TreasureRoomDoor {

    private TreasureRoomDoor treasureRoomDoor;
    private boolean activeWriter = false;
    private int activeReaders = 0;
    private final Logger logger = Logger.getLogger(GuardsMan.class.getName());

    public GuardsMan(TreasureRoomDoor treasureRoomDoor) {
        this.treasureRoomDoor = treasureRoomDoor;
    }

    @Override
    public synchronized void add(Valuable valuable) {
        acquireWrite();
        try {
            treasureRoomDoor.add(valuable);
        } finally {
            releaseWrite();
        }
    }

    @Override
    public Valuable retrieve() throws Exception {
        acquireWrite();
        try {
            return treasureRoomDoor.retrieve();
        } finally {
            releaseWrite();
        }
    }

    @Override
    public java.util.List<org.app.valuables.Valuable> readValuables() {
        acquireRead();
        try {
            treasureRoomDoor.readValuables();
        } finally {
            releaseRead();
        }
        return null;
    }

    @Override
    public synchronized void acquireRead() {
        while (activeWriter) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.warning("Thread interrupted: " + e.getMessage());
            }
        }
        activeReaders++;
    }

    @Override
    public synchronized void acquireWrite() {
        while (activeWriter || activeReaders > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.warning("Thread interrupted: " + e.getMessage());
            }
        }
        activeWriter = true;
    }

    @Override
    public synchronized void releaseRead() {
        activeReaders--;
        if (activeReaders == 0) {
            notifyAll();
        }
    }

    @Override
    public synchronized void releaseWrite() {
        activeWriter = false;
        notifyAll();
    }
}
