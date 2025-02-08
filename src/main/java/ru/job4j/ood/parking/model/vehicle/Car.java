package ru.job4j.ood.parking.model.vehicle;

public class Car extends Vehicle {
    private final int size;

    public Car(String name,
               String registerNumber) {
        super(name, registerNumber);
        this.size = 1;
    }

    public int getSize() {
        return size;
    }
}