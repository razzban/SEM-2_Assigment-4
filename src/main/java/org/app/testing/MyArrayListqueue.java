package org.app.testing;

/**
 * Defines the interface for a list collection - an abstract data type List.
 * Elements are referenced by contiguous numeric indices. This list allows duplicate elements
 * and may also permit <code>null</code> elements depending on implementation.
 *
 * @param <T> the data type of elements in the collection
 * @author Steffen Vissing Andersen
 * @version 1.0 (versioned by Lewis and Chase on 12/08/2008)
 */
public interface MyArrayListqueue<T> {

    /**
     * Inserts the specified element at the specified position in the list.
     *
     * @param index   the index at which the element is to be inserted.
     *                Valid index ranges from 0 to the list size, inclusive.
     * @param element the element to be inserted
     * @throws IllegalStateException     if the list is full, particularly when inserting at the end.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size()).
     * @throws IllegalArgumentException  if the element cannot be inserted (e.g., if null elements are not allowed).
     */
    void add(int index, T element);

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element the element to be added
     * @throws IllegalStateException    if the list is full
     * @throws IllegalArgumentException if the element cannot be added (e.g., if null elements are not allowed).
     */
    void add(T element);

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index   the index of the element to replace
     * @param element the element to be stored at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size()).
     * @throws IllegalArgumentException  if the element cannot be set (e.g., if null elements are not allowed).
     */
    void set(int index, T element);

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index the index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size()).
     */
    T get(int index);

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size()).
     */
    T remove(int index);

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     *
     * @param element the element to be removed from this list, if present
     * @return the element that was removed from the list
     * @throws IllegalStateException if the element is not found in the list
     */
    T remove(T element);

    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     *
     * @param element the element to search for
     * @return the index of the first occurrence of the specified element in this list,
     *         or -1 if this list does not contain the element
     */
    int indexOf(T element);

    /**
     * Returns <code>true</code> if this list contains the specified element.
     *
     * @param element the element whose presence in this list is to be tested
     * @return <code>true</code> if this list contains the specified element; <code>false</code> otherwise
     */
    boolean contains(T element);

    /**
     * Returns <code>true</code> if this list contains no elements.
     *
     * @return <code>true</code> if this list is empty; <code>false</code> otherwise
     */
    boolean isEmpty();

    /**
     * Returns <code>true</code> if this list is full.
     *
     * @return <code>true</code> if this list is full; <code>false</code> otherwise
     */
    boolean isFull();

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    int size();
}
