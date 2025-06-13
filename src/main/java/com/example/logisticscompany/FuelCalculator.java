package com.example.logisticscompany;

public class FuelCalculator {
    private final double pricePerGallon = 4.5;

    /**
     * Розраховує потрібний об'єм палива і вартість.
     * @param miles Кількість миль.
     * @param fuelEfficiency Ефективність використання палива (MPG).
     * @return Масив {паливо необхідне (галони), вартість ($)}.
     */
    public double[] calculateFuel(double miles, double fuelEfficiency) {
        if (fuelEfficiency <= 0 || miles <= 0) {
            throw new IllegalArgumentException("Miles and fuel efficiency must be greater than 0.");
        }

        double fuelNeeded = miles / fuelEfficiency;
        double cost = fuelNeeded * pricePerGallon;

        return new double[]{fuelNeeded, cost};
    }

}
