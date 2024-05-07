package org.app.valuables;

public class Silver extends ValuableMultiton implements Valuable {
    private static final String name = "Silver";
    private static final double value = 200.0;

    private Silver() {}

    public static Valuable getInstance(String silver, double v) {
        return ValuableMultiton.getInstance(name, new Silver());
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