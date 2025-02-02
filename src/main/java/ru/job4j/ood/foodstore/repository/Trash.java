package ru.job4j.ood.foodstore.repository;

import ru.job4j.ood.foodstore.model.Food;
import ru.job4j.ood.foodstore.services.ShelfLifeCalculator;

public class Trash extends AbstractStore {

    @Override
    public void addProduct(Food food) {
        int remainingShelfLife = ShelfLifeCalculator.calculateRemainingShelfLifePercentage(food);
        if (remainingShelfLife == 100) {
            products.add(food);
        }
    }
}