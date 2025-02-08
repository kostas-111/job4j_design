package ru.job4j.ood.parking.model.vehicle;

public class Truck extends Vehicle {

    private final int size;

    public Truck(String name,
                 String registerNumber,
                 int size) {
        super(name, registerNumber);
        this.size = checkSize(size);
    }

    public int getSize() {
        return size;
    }

    private int checkSize(int size) {
        if (size == 1) {
            throw new IllegalArgumentException("Размер для грузового транспортного средства должен быть больше 1");
        }
        return size;
    }
}