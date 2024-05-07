package org.app.valuables;

public class Gold extends ValuableMultiton implements Valuable {
    private static final String name = "Gold";
    private static final double value = 300.0;

    private Gold() {}

    public static Valuable getInstance(String gold, double v) {
        return ValuableMultiton.getInstance(name, new Gold());
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