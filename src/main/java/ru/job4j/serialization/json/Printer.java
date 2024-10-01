package ru.job4j.serialization.json;

import java.util.Arrays;

public class Printer {
    private boolean isColour;
    private Producer producer;
    private double price;
    private String[] typesOfConnection;

    public Printer(boolean isColour, Producer producer, double price, String... typesOfConnection) {
        this.isColour = isColour;
        this.producer = producer;
        this.price = price;
        this.typesOfConnection = typesOfConnection;
    }

    public Printer(boolean isColour, double price, String... typesOfConnection) {
        this.isColour = isColour;
        this.price = price;
        this.typesOfConnection = typesOfConnection;
    }

    public boolean isColour() {
        return isColour;
    }

    public Producer getProducer() {
        return producer;
    }

    public double getPrice() {
        return price;
    }

    public String[] getTypesOfConnection() {
        return typesOfConnection;
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