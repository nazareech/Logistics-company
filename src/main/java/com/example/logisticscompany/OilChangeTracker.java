package com.example.logisticscompany;


import java.util.HashMap;
import java.util.Map;

// Oil change tracker adapted to work with Vehicle objects
public class OilChangeTracker {

    private final Map<Vehicle, Double> vehicleMileage = new HashMap<>();

    /**
     * Adds a vehicle with an initial mileage to the tracker.
     * @param vehicle The Vehicle object.
     * @param mileage Initial mileage for the vehicle.
     */
    public void addVehicle(Vehicle vehicle, double mileage) {
        vehicleMileage.put(vehicle, mileage);
    }

    /**
     * Updates the mileage of a vehicle.
     * @param vehicle The Vehicle object.
     * @param miles Additional miles the vehicle has traveled.
     */
    public void updateMileage(Vehicle vehicle, double miles) {
        if (vehicleMileage.containsKey(vehicle)) {
            vehicleMileage.put(vehicle, vehicleMileage.get(vehicle) + miles);
        } else {
            System.out.println("Vehicle not found in the tracker.");
        }
    }

    /**
     * Checks if an oil change is due for a vehicle.
     * @param vehicle The Vehicle object.
     */
    public String checkOilChange(Vehicle vehicle) {
        String output;

        if (vehicleMileage.containsKey(vehicle)) {
            double milesSinceLastChange = vehicleMileage.get(vehicle);
            if (milesSinceLastChange >= 10000) {
               output = "Oil change is due for " + vehicle.getModel() + " (" + vehicle.getManufacturer() + ").";
                return output;
            } else {
                output = "Oil change is not due for " + vehicle.getModel() + ". Miles until next change: " + (10000 - milesSinceLastChange);
                return output;
            }
        } else {
            output = "Vehicle not found in the tracker.";
            return output;
        }
    }
}
