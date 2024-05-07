package org.app.testing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class ArrayListAdapter<T> extends ArrayList<T> implements Queue<T> {

    @Override
    public boolean offer(T t) {
        return super.add(t);
    }

    @Override
    public T remove() {
        return super.remove(0);
    }

    @Override
    public T poll() {
        if (super.isEmpty()) {
            return null;
        } else {
            return super.remove(0);
        }
    }

    @Override
    public T element() {
        return super.get(0);
    }

    @Override
    public T peek() {
        if (super.isEmpty()) {
            return null;
        } else {
            return super.get(0);
        }
    }
}