package ru.job4j.ood.parking.model.parkingplace;

import ru.job4j.ood.parking.model.vehicle.Vehicle;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CarPlace implements ParkingPlace {

    private final int placeNumber;

    private boolean isBusy;

    private final boolean isCarPlace = true;

    public CarPlace(int placeNumber, boolean isBusy) {
        this.placeNumber = placeNumber;
        this.isBusy = isBusy;
    }

    /*
    Метод отвечает за парковку транспортного средства (Vehicle) на стоянке.
    Его основная логика зависит от размера транспортного средства.
     */
    @Override
    public void park(Vehicle vehicle, List<ParkingPlace> parkingPlaceList) {
        int sizeVehicle = vehicle.getSize();
        if (sizeVehicle == 1) {
            for (ParkingPlace place : parkingPlaceList) {
                if (!place.isBusy()) {
                    isBusy = true;
                }
            }
        }

        if (sizeVehicle > 1) {
            List<ParkingPlace> freeSequence = findFreeSequence(sizeVehicle, parkingPlaceList);
            if (!freeSequence.isEmpty()) {
                freeSequence.forEach(place -> place.setBusy(true));
            }
            if (freeSequence.isEmpty()) {
                throw new IllegalStateException("Необходимой последованости свободных мест для парковки нелегкового авто нет.");
            }
        }
    }

    /*
    Метод находит первую подходящую последовательность свободных мест и возвращает её.
    Цикл заканчивается раньше, чем полный проход по всему списку, потому что нам не нужны проверки после тех позиций,
    где физически не может поместиться требуемое количество мест подряд.
    Далее - проверяет, является ли текущая последовательность длиной sizeVehicle свободной.
    Если хотя бы одно место в проверяемой последовательности занято, переменная found становится равной false,
    и поиск продолжается дальше.
    Если последовательность свободных мест найдена, она добавляется в результат.
    Как только найденная последовательность добавлена в результат, выполнение цикла прерывается.
     */
    private List<ParkingPlace> findFreeSequence(int sizeVehicle, List<ParkingPlace> parkingPlaceList) {
        List<ParkingPlace> result = new ArrayList<>();
        for (int i = 0; i < parkingPlaceList.size() - sizeVehicle + 1; i++) {
            boolean found = true;
            for (int j = 0; j < sizeVehicle; j++) {
                if (parkingPlaceList.get(i + j).isBusy()) {
                    found = false;
                    break;
                }
            }
            if (found) {
                for (int k = 0; k < sizeVehicle; k++) {
                    result.add(parkingPlaceList.get(i + k));
                }
                break;
            }
        }
        return result;
    }

    @Override
    public void unpark(Vehicle vehicle, List<ParkingPlace> parkingPlaceList) {
        int sizeVehicle = vehicle.getSize();
        if (sizeVehicle == 1) {
            isBusy = false;
        }
        if (sizeVehicle > 1) {
            List<ParkingPlace> occupiedSequence = findOccupiedSequence(sizeVehicle, parkingPlaceList);
            occupiedSequence.forEach(place -> place.setBusy(false));
        }
    }

    /*
     Метод ищет последовательность занятых мест, соответствующих размеру автомобиля.
     Он возвращает список объектов ParkingPlace, которые заняты данным автомобилем
     */
    private List<ParkingPlace> findOccupiedSequence(int sizeVehicle, List<ParkingPlace> parkingPlaceList) {
        List<ParkingPlace> result = new ArrayList<>();
        for (int i = 0; i < parkingPlaceList.size() - sizeVehicle + 1; i++) {
            boolean found = true;
            for (int j = 0; j < sizeVehicle; j++) {
                if (!parkingPlaceList.get(i + j).isBusy()) {
                    found = false;
                    break;
                }
            }
            if (found) {
                for (int k = 0; k < sizeVehicle; k++) {
                    result.add(parkingPlaceList.get(i + k));
                }
                break;
            }
        }
        return result;
    }

    @Override
    public int getPlaceNumber() {
        return placeNumber;
    }

    @Override
    public boolean isBusy() {
        return isBusy;
    }

    @Override
    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public boolean isCarPlace() {
        return isCarPlace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarPlace carPlace = (CarPlace) o;
        return placeNumber == carPlace.placeNumber && isBusy == carPlace.isBusy && isCarPlace == carPlace.isCarPlace;
    }

    @Override
    public int hashCode() {
        return Objects.hash(placeNumber, isBusy, isCarPlace);
    }

    @Override
    public String toString() {
        return "CarPlace{"
                + "placeNumber=" + placeNumber
                + ", isBusy=" + isBusy
                + ", isCarPlace=" + isCarPlace
                + '}';
    }
}
