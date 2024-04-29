package org.app.valuables;


public class Diamond extends ValuableMultiton implements Valuable {
    private static final String name = "Diamond";
    private static final double value = 500.0;

    private Diamond() {}

    public static Valuable getInstance() {
        return ValuableMultiton.getInstance(name, new Diamond());
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