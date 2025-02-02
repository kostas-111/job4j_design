package ru.job4j.ood.lsp.myexamples.second;

public abstract class Vehicle {
    String model;
    int maxSpeed;

    public Vehicle(String model, int maxSpeed) {
        this.model = model;
        this.maxSpeed = maxSpeed;
    }

    public abstract void drive();
}