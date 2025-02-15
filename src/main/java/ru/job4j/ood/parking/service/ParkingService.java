package ru.job4j.ood.parking.service;


import ru.job4j.ood.parking.model.parkingplace.ParkingPlace;
import ru.job4j.ood.parking.model.vehicle.Vehicle;
import java.util.List;
import java.util.Optional;

public class ParkingService {

    private final ParkingCreator parkingCreator;

    private final List<ParkingPlace> parking;

    public ParkingService(ParkingCreator parkingCreator) {
        this.parkingCreator = parkingCreator;
        this.parking = parkingCreator.getParkingPlaceList();
    }

    /*
    Метод парковки ТС на паркинг. Если ТС автомобиль (размер 1), ищем только место для автомобиля.
    Если это грузовик (размер > 1), сначала ищем место для грузовика, а затем, если не находим, ищем для него место на паркови для авто.
    Обработка всех возможных ситуаций: Если для грузовика не нашлось места, мы пробуем найти место для автомобиля.
    Если и здесь неудача, выводится сообщение о том, что нет свободных мест для грузовика.
     */
    public void pullVehicle(Vehicle vehicle) {
        int size = vehicle.getSize();

        if (size == 1) {
            Optional<ParkingPlace> optionalCarPlace = parkingCreator.findFreeCarPlace();
            optionalCarPlace.ifPresentOrElse(
                    place -> place.park(vehicle, parking),
                    () -> System.out.println("Нет свободных мест для автомобиля.")
            );
        } else {
            Optional<ParkingPlace> optionalTruckPlace = parkingCreator.findFreeTruckPlace();
            optionalTruckPlace.ifPresentOrElse(
                    place -> place.park(vehicle, parking),
                    () -> {
                        Optional<ParkingPlace> optionalCarPlace = parkingCreator.findFreeCarPlace();
                        optionalCarPlace.ifPresentOrElse(
                                place -> place.park(vehicle, parking),
                                () -> System.out.println("Нет свободных мест для грузовика.")
                        );
                    }
            );
        }
    }

    /*
    Метод выезда ТС с парковки. Тут сделал простую реализацию, которая не учитывает,
    занял ли до этого грузовик место на своей парковке или на парковке для авто.
    Здесь просто ищем первое занятое место в зависимости от типа выезжаемого ТС - и освобождаем его.
    Как вариант - заморочиться и доработать идейно так - сделать Мапу, где ключ - это объект ТС,
    а значение - массив занятых им парковочных мест. При паровке - пишем туда значения.
     При выезде - ищем в Мапе по ключу какие номера и типы парковочных мест заняло ТС,
     и меняем в них флаг isBusy с true на false
     */
    public void pushVehicle(Vehicle vehicle) {
        int size = vehicle.getSize();

        if (size == 1) {
            Optional<ParkingPlace> optionalCarPlace = parkingCreator.findBusyCarPlace();
            optionalCarPlace.ifPresent(
                    place -> place.unpark(vehicle, parking)
            );
        } else  {
            Optional<ParkingPlace> optionalTruckPlace = parkingCreator.findBusyTruckPlace();
            optionalTruckPlace.ifPresent(
                    place -> place.unpark(vehicle, parking)
            );
        }
    }
}