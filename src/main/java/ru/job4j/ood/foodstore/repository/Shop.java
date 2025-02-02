package ru.job4j.ood.foodstore.repository;

import ru.job4j.ood.foodstore.model.Food;
import ru.job4j.ood.foodstore.services.ShelfLifeCalculator;

public class Shop extends AbstractStore {

    @Override
    public void addProduct(Food food) {
        int remainingShelfLife = ShelfLifeCalculator.calculateRemainingShelfLifePercentage(food);
        if (remainingShelfLife > 25 && remainingShelfLife < 100) {
            products.add(food);
            if (remainingShelfLife > 75) {
               for (Food product : products) {
                   product.setDiscount(0.2);
                   product.setPrice(product.getPrice() * 0.8);
               }
            }
        }
    }
}