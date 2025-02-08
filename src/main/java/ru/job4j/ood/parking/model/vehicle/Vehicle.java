package ru.job4j.ood.parking.model.vehicle;

public abstract class Vehicle {

    private final String name;
    private final String registerNumber;

    public Vehicle(String name, String registerNumber) {
        this.name = name;
        this.registerNumber = registerNumber;
    }

    public String getName() {
        return name;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }
}
