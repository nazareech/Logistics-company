package com.example.logisticscompany;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LocationAndConditionsManager {
    private static final LocationAndConditionsManager instance = new LocationAndConditionsManager();

    private final ObservableList<LocationAndConditions> locationAndConditions;

    public LocationAndConditionsManager() {
        locationAndConditions = FXCollections.observableArrayList(
        new LocationAndConditions("New York", "Heavy traffic, only evening pickups allowed."),
        new LocationAndConditions("Los Angeles", "Open for pickups all day."),
        new LocationAndConditions("Chicago", "Pickup not available due to weather.")
        );
    }

  public static LocationAndConditionsManager getInstance(){
        return instance;
  }
  public ObservableList<LocationAndConditions> getLocationAndConditions(){
        return locationAndConditions;
  }
  public void addLocationAndConditions(LocationAndConditions newCondition){
        locationAndConditions.add(newCondition);
  }
}
