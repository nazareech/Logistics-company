package com.example.logisticscompany;

public class DispatcherSalaryCalculator {
    private double shipmentRevenue;
    private double fuelCost;

    public String calculateSalary(double shipmentRevenue, double fuelCost ){

        if(shipmentRevenue < fuelCost){
            return "Error: Fuel cost cannot exceed shipment revenue.";
        }
        
        double netRevenue = shipmentRevenue - fuelCost;
        double salary = netRevenue * 0.025;

        String result = "Dispatcher's salary (2.5% of net revenue): $"+ salary;
        return result;
    }
}
