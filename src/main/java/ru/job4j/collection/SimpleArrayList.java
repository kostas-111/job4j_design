package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount = 0;

    public SimpleArrayList(int size) {
        this.container = (T[]) new Object[size];
    }

    private T[] grow() {
        int newSize;
        if (size == 0) {
            newSize = 1;
        } else {
            newSize = size * 2;
        }
        return Arrays.copyOf(container, newSize);
    }

    @Override
    public void add(T value) {
        if (container.length == size) {
            container = grow();
        }
        container[size++] = value;
        this.modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        if (container.length == size) {
            container = grow();
        }
        T result = get(index);
        System.arraycopy(container, index, container, index + 1, size - index);
        container[index] = newValue;
        return result;
    }

    @Override
    public T remove(int index) {
        T result = get(index);
        int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(container, index + 1, container, index, newSize - index);
        }
        size = newSize;
        container[size] = null;
        this.modCount++;
        return result;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {

        int expectedModCount = modCount;
        return new Iterator<T>() {
            private int count = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[count++];
            }
        };
    }
}