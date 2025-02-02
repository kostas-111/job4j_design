package ru.job4j.ood.foodstore.services;

import ru.job4j.ood.foodstore.model.Food;
import ru.job4j.ood.foodstore.repository.Store;

import java.util.ArrayList;
import java.util.List;

/*
Класс ControlQuality использует агрегацию для хранения списка хранилищ (stores).
Он управляет этими хранилищами, но не владеет ими напрямую.
Связь между ControlQuality и хранилищами является слабосвязанной,
так как используется интерфейс Store
ControlQuality не зависит от конкретной реализации хранилищ,
а оперирует с ними через интерфейс Store.
Это позволяет легко заменять или добавлять новые типы хранилищ без
изменения основного функционала
 */
public class ControlQuality {
    private final List<Store> stores = new ArrayList<>();

    // Добавляем хранилище
    public void addStore(Store store) {
        stores.add(store);
    }

    // Удаляем хранилище
    public void removeStore(Store store) {
        stores.remove(store);
    }

    /*
    проходит по всем доступным хранилищам и пытается распределить каждый продукт.
    Если ни одно хранилище не подходит, выбрасывается исключение.
     */

    public void distribute(List<Food> foods) {
        for (Food food : foods) {
            double remainingShelfLife = ShelfLifeCalculator.calculateRemainingShelfLifePercentage(food);

            boolean distributed = false;

            for (Store store : stores) {
                if (!distributed) {
                    if (remainingShelfLife > 75) { // Срок годности менее 25%
                        store.addProduct(food);
                        distributed = true;
                    } else if (remainingShelfLife >= 25 && remainingShelfLife <= 75) { // Срок годности от 25% до 75%
                        store.addProduct(food);
                        distributed = true;
                    } else if (remainingShelfLife < 25) { // Срок годности менее 25%, но не истекший
                        food.setPrice(food.getPrice() * 0.8); // Скидка 20%
                        store.addProduct(food);
                        distributed = true;
                    } else { // Срок годности истек
                        store.addProduct(food);
                        distributed = true;
                    }
                }
            }

            if (!distributed) {
                throw new IllegalStateException("Не удалось распределить продукт: " + food.getName());
            }
        }
    }
}
