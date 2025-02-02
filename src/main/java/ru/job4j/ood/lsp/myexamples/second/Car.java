package ru.job4j.ood.lsp.myexamples.second;

public class Car extends Vehicle {

    public Car(String model, int maxSpeed) {
        super(model, maxSpeed);
    }

    @Override
    public void drive() {
        System.out.println("Автомобиль " + model + " едет со скоростью до " + maxSpeed + " км/ч.");
    }
}
