package org.app.rooms;


import org.app.valuables.Valuable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Room implements TreasureRoomDoor {

    private final List<Valuable> valuables = new ArrayList<>();
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();

    @Override
    public void add(Valuable valuable) {
        writeLock.lock();
        try {
            valuables.add(valuable);
            System.out.println("Valuable added to treasure room: " + valuable.getName());
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public Valuable retrieve() throws Exception {
        writeLock.lock();
        try {
            if (valuables.isEmpty()) {
                throw new Exception("No valuables to retrieve.");
            }
            Valuable valuable = valuables.remove(0);
            System.out.println("Valuable retrieved: " + valuable.getName());
            return valuable;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public java.util.List<org.app.valuables.Valuable> readValuables() {
        readLock.lock();
        try {
            System.out.println("Reading all valuables:");
            valuables.forEach(valuable -> System.out.println(valuable.getName()));
        } finally {
            readLock.unlock();
        }
        return null;
    }

    @Override
    public void acquireRead() {
        readLock.lock();
    }

    @Override
    public void acquireWrite() {
        writeLock.lock();
    }

    @Override
    public void releaseRead() {
        readLock.unlock();
    }

    @Override
    public void releaseWrite() {
        writeLock.unlock();
    }
}
