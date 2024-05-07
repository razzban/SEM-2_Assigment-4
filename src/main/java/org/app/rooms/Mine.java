package org.app.rooms;
import org.app.valuables.Valuable;
import org.app.valuables.Diamond;
import org.app.valuables.Gold;
import org.app.valuables.Silver;
import org.app.valuables.WoodenCoin;
import org.app.valuables.Ruby;

import java.util.Random;

public class Mine {
    private Random random = new Random();

    public Valuable getValuable() {
        int a = random.nextInt(15);

        if (a < 6) {
            return Silver.getInstance("Silver", 20.0);
        } else if (a < 10) {
            return Gold.getInstance("Gold", 50.0);
        } else if (a < 11) {
            return Diamond.getInstance("Diamond", 100.0);
        } else if (a < 13) {
            return WoodenCoin.getInstance("Wooden Coin", 0.0);
        } else {
            return Ruby.getInstance("Ruby", 75.0);
        }
    }
}

