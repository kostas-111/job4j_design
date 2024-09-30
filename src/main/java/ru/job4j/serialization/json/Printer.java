package ru.job4j.serialization.json;

import java.util.Arrays;

public class Printer {
    private final boolean isColour;
    private final Producer producer;
    private final double price;
    private final String[] typesOfConnection;

    public Printer(boolean isColour, Producer producer, double price, String[] typesOfConnection) {
        this.isColour = isColour;
        this.producer = producer;
        this.price = price;
        this.typesOfConnection = typesOfConnection;
    }

    @Override
    public String toString() {
        return "Printer{"
                + "isColour=" + isColour
                + ", producer=" + producer
                + ", price=" + price
                + ", typesOfConnection=" + Arrays.toString(typesOfConnection)
                + '}';
    }
}