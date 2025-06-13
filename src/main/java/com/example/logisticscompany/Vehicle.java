package com.example.logisticscompany;

import javafx.beans.property.SimpleStringProperty;

// Vehicle model class
public class Vehicle {
    private final SimpleStringProperty model;
    private final SimpleStringProperty manufacturer;
    private final SimpleStringProperty weight;

    public Vehicle(String model, String manufacturer, String weight) {
        this.model = new SimpleStringProperty(model);
        this.manufacturer = new SimpleStringProperty(manufacturer);
        this.weight = new SimpleStringProperty(weight);
    }

    public String getModel() { return model.get(); }
    public String getManufacturer() { return manufacturer.get(); }
    public String getWeight() { return weight.get(); }

    @Override
    public String toString() {
        return model.get() + " (" + manufacturer.get() + ") - " + weight.get() + " lbs";
    }
}

