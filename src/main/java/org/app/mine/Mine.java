package org.app.mine;
import org.app.valuables.Valuable;
import org.app.valuables.Diamond;
import org.app.valuables.Gold;
import org.app.valuables.Silver;
import org.app.valuables.WoodenCoin;
import org.app.valuables.Ruby;

import java.util.Random;

public class Mine {
    private Random random = new Random(); // Use a single Random instance for better performance and randomness.

    public Valuable getValuable() {
        int a = random.nextInt(15); // Generates a number from 0 to 14

        if (a < 6) { // 0-5: Six possibilities
            return Silver.getInstance(); // Common and less value
        } else if (a < 10) { // 6-9: Four possibilities
            return Gold.getInstance(); // Rare and good value
        } else if (a < 11) { // 10: One possibility
            return Diamond.getInstance(); // Super rare but massive value
        } else if (a < 13) { // 11-12: Two possibilities
            return WoodenCoin.getInstance(); // Unlucky sometimes
        } else { // 13-14: Two possibilities
            return Ruby.getInstance(); // Rare and excellent value
        }
    }
}
