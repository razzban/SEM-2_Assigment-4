package org.app.valuables;




import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Diamond implements Valuable {
    private static final Map<String, Diamond> instances = new ConcurrentHashMap<>();

    private final String name;
    private final double value;

    private Diamond(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public static Valuable getInstance(String name, double value) {
        return instances.computeIfAbsent(name, k -> new Diamond(name, value));
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