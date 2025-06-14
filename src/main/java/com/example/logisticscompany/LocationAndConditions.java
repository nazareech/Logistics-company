package com.example.logisticscompany;

import javafx.beans.property.SimpleStringProperty;

public class LocationAndConditions {
    private final SimpleStringProperty location;
    private final SimpleStringProperty conditions;

    public LocationAndConditions(String location, String conditions) {
        this.location = new SimpleStringProperty(location);
        this.conditions = new SimpleStringProperty(conditions);
    }

    public String getLocation() {
        return location.get();
    }

    public SimpleStringProperty locationProperty() {
        return location;
    }

    public String getConditions() {
        return conditions.get();
    }

    public SimpleStringProperty conditionsProperty() {
        return conditions;
    }

}
