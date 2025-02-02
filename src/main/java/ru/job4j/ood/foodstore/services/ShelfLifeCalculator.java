package ru.job4j.ood.foodstore.services;

import ru.job4j.ood.foodstore.model.Food;
import java.time.LocalDate;

/*
 Класс, отвечающий за расчет оставшегося срока годности
 соответствует принципу единственной ответственности (SRP)
 */
public class ShelfLifeCalculator {
    public static double calculateRemainingShelfLifePercentage(Food food) {
        LocalDate now = LocalDate.now();
        long totalDays = daysBetween(food.getCreateDate(), food.getExpiryDate());
        long elapsedDays = daysBetween(food.getCreateDate(), now);

        if (totalDays == 0) {
            return 100.0; // если дата истечения совпадает с датой создания, считаем что срок годности уже истек
        }

        return ((double) (totalDays - elapsedDays) / totalDays) * 100;
    }

    private static long daysBetween(LocalDate start, LocalDate end) {
        return end.toEpochDay() - start.toEpochDay();
    }
}
