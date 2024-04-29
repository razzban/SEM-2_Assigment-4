package org.app.testing;
import java.util.ArrayList;


public class MyArrayListTest<T> implements MyArrayList<T> {
    private T[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayListTest() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == elements.length) {
            resize();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public void add(T element) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = element;
    }

    @Override
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        elements[index] = element;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return elements[index];
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        T element = elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null; // prevent memory leak
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new IllegalStateException("Element not found");
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        // This example assumes the list is never full as it can resize.
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize() {
        int newSize = elements.length * 2;
        T[] newElements = (T[]) new Object[newSize];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }
}
