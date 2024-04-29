package org.app.valuables;

public class WoodenCoin extends ValuableMultiton implements Valuable {
    private static final String name = "Wooden Coin";
    private static final double value = 50.0;

    private WoodenCoin() {}

    public static Valuable getInstance() {
        return ValuableMultiton.getInstance(name, new WoodenCoin());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getValue() {
        return value;
    }
}