package ru.job4j.ood.foodstore.services;

import ru.job4j.ood.foodstore.model.Food;
import java.time.LocalDate;

/*
 Класс, отвечающий за расчет оставшегося срока годности
 соответствует принципу единственной ответственности (SRP)
 */
public class ShelfLifeCalculator {

    public static int calculateRemainingShelfLifePercentage(Food food) {
        LocalDate now = LocalDate.now();
        LocalDate expiryDate = food.getExpiryDate();
        LocalDate createDate = food.getCreateDate();
        long totalDays = daysBetween(createDate, expiryDate);
        long elapsedDays = daysBetween(createDate, now);
        int result = 100;
        if (!(now.isAfter(expiryDate)) || now.equals(expiryDate)) {
            double resultDouble = ((double) (elapsedDays  * 100) / totalDays);
            result = (int) Math.round(resultDouble);
        }
        return result;
    }

    private static long daysBetween(LocalDate start, LocalDate end) {
        return end.toEpochDay() - start.toEpochDay();
    }
}