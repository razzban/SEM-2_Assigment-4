package org.app.valuables;

public class Ruby extends ValuableMultiton implements Valuable {
    private static final String name = "Ruby";
    private static final double value = 400.0;

    private Ruby() {}

    public static Valuable getInstance() {
        return ValuableMultiton.getInstance(name, new Ruby());
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