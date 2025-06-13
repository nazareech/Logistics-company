package com.example.logisticscompany;

import javafx.collections.ObservableList;

public class WeightCalculator {

    private final double maxAllowedWeight = 11300;

    /**
     * Розраховує загальну вагу обраних транспортних засобів.
     * @param selectedVehicles Список обраних транспортних засобів.
     * @return Загальна вага.
     */
    public double calculateWeight(ObservableList<Vehicle> selectedVehicles) {
        double totalWeight = 0;

        for (Vehicle vehicle : selectedVehicles) {
            double weight = Double.parseDouble(vehicle.getWeight());
            totalWeight += weight;

            // Якщо вага перевищує максимальну, повідомляємо і завершуємо обчислення
            if (totalWeight > maxAllowedWeight) {
                System.out.println("Total weight exceeds the allowed limit of " + maxAllowedWeight + " lbs.");
                break;
            }
        }

        return totalWeight;
    }


}
