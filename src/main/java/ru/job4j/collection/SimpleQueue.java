package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    int sizeIn = 0;
    int sizeOut = 0;

    public T poll() {
        if (sizeIn == 0 && sizeOut == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (sizeIn != 0) {
            for (int i = 0; i < sizeIn; i++) {
                output.push(input.pop());
            }
            sizeOut = sizeIn;
            sizeIn = 0;
        }
        sizeOut--;
        return output.pop();
    }

    public void push(T value) {
        if (sizeOut != 0) {
            for (int i = 0; i < sizeOut; i++) {
                input.push(output.pop());
            }
            sizeIn = sizeOut;
            sizeOut = 0;
        }
        input.push(value);
        sizeIn++;
    }
}