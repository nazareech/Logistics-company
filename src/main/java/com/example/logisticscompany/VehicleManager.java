package com.example.logisticscompany;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VehicleManager {
    private static final VehicleManager instance = new VehicleManager();

    private final ObservableList<Vehicle> vehicles; // Список транспортних засобів

    // Приватний конструктор для забезпечення синглтонності
    private VehicleManager() {
        vehicles = FXCollections.observableArrayList(
                new Vehicle("A4", "Audi", "3620"),
                new Vehicle("3 Series", "BMW", "3580"),
                new Vehicle("Equinox", "Chevrolet", "3700"),
                new Vehicle("Silverado", "Chevrolet", "4500"),
                new Vehicle("Explorer", "Ford", "4350"),
                new Vehicle("F-150", "Ford", "4050"),
                new Vehicle("Accord", "Honda", "3130"),
                new Vehicle("CR-V", "Honda", "3330"),
                new Vehicle("Sonata", "Hyundai", "3350"),
                new Vehicle("Grand Cherokee", "Jeep", "4650"),
                new Vehicle("Optima", "Kia", "3320"),
                new Vehicle("CX-5", "Mazda", "3660"),
                new Vehicle("C-Class", "Mercedes-Benz", "3625"),
                new Vehicle("Altima", "Nissan", "3200"),
                new Vehicle("1500", "Ram", "4800"),
                new Vehicle("Outback", "Subaru", "3600"),
                new Vehicle("Model 3", "Tesla", "3550"),
                new Vehicle("Camry", "Toyota", "3340"),
                new Vehicle("RAV4", "Toyota", "3370"),
                new Vehicle("Passat", "Volkswagen", "3250")
        );
    }

    // Метод доступу до екземпляра синглтона
    public static VehicleManager getInstance() {
        return instance;
    }

    // Повертає список транспортних засобів
    public ObservableList<Vehicle> getVehicles() {
        return vehicles;
    }

    // Додає новий транспортний засіб
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }
}

