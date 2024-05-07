package org.app.valuables;

import org.app.valuables.Valuable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ValuableMultiton {

    private static final Map<String, Valuable> instances = new ConcurrentHashMap<>();

    // Protected constructor to prevent instantiation outside subclass
    protected ValuableMultiton() {}

    public static Valuable getInstance(String key, Valuable instance) {
        instances.putIfAbsent(key, instance);
        return instances.get(key);
    }
}
