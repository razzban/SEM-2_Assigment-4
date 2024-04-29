package org.app.door;

import org.app.valuables.Valuable;

public interface TreasureRoomDoor {

    void add(Valuable valuable);
    Valuable retrieve() throws Exception;
    java.util.List<org.app.valuables.Valuable> readValuables();
    void acquireRead();
    void acquireWrite();
    void releaseRead();
    void releaseWrite();
}
