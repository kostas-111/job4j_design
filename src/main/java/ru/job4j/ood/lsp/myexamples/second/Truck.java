package ru.job4j.ood.lsp.myexamples.second;

/*
У нас есть базовый класс Vehicle, который описывает транспортное средство, и два его подкласса:
Car (автомобиль) и Truck (грузовик).
В данном примере метод drive() в классе Truck нарушает принцип LSP,
так как добавляет дополнительную проверку на вес груза, которую нет в базовом классе Vehicle.
Это означает, что код, рассчитанный на работу с объектами типа Vehicle, может неожиданно остановиться,
если передать ему объект типа Truck с большим весом груза.
 */
public class Truck extends Vehicle {
    private final int cargoWeight;

    public Truck(String model, int maxSpeed, int cargoWeight) {
        super(model, maxSpeed);
        this.cargoWeight = cargoWeight;
    }

    @Override
    public void drive() {
        if (cargoWeight > 5000) {
            System.out.println("Грузовик " + model + " перегружен и не может ехать.");
        } else {
            System.out.println("Грузовик " + model + " едет со скоростью до " + maxSpeed + " км/ч.");
        }
    }
}