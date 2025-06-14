package com.example.logisticscompany;

public class PickupConditionsChecker {

    private final LocationAndConditionsManager locationManager = LocationAndConditionsManager.getInstance();

    /**
     * Додає або оновлює умови для вказаного місця.
     * @param location Місце забору.
     * @param condition Умови для цього місця.
     */
    public void setCondition(String location, String condition) {
        // Шукаємо локацію, яка існує
        boolean updated = false;
        for (LocationAndConditions locAndCond : locationManager.getLocationAndConditions()) {
            if (locAndCond.getLocation().equalsIgnoreCase(location)) {
                // Оновлюємо умови, якщо знайдено
                locAndCond.conditionsProperty().set(condition);
                updated = true;
                break;
            }
        }

        // Якщо місце не знайдено, додаємо нову локацію з умовами
        if (!updated) {
            locationManager.addLocationAndConditions(new LocationAndConditions(location, condition));
        }
    }

    /**
     * Виводить умови для вказаного місця.
     * Якщо місця не існує, виводить, що місце не знайдене.
     * @param location Місце забору.
     */
    public void getCondition(String location) {
        for (LocationAndConditions locAndCond : locationManager.getLocationAndConditions()) {
            if (locAndCond.getLocation().equalsIgnoreCase(location)) {
                System.out.println("Conditions for " + location + ": " + locAndCond.getConditions());
                return;
            }
        }
        System.out.println("Location not found.");
    }
}