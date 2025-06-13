package com.example.logisticscompany;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;

public class VehicleSelectionController {
    @FXML private TableView<Vehicle> vehicleTable;
    @FXML private TableColumn<Vehicle, String> modelColumn;
    @FXML private TableColumn<Vehicle, String> manufacturerColumn;
    @FXML private TableColumn<Vehicle, String> weightColumn;
    @FXML private Label selectionCountLabel;
    @FXML private Button submitButton;

    private ObservableList<Vehicle> selectedVehicles = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize table columns
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));

        // Load sample data
        ObservableList<Vehicle> vehicles = FXCollections.observableArrayList(
                new Vehicle("Model X100", "Freightliner", "10,000"),
                new Vehicle("Model Y200", "Kenworth", "12,000"),
                new Vehicle("Model Z300", "Volvo", "11,500"),
                new Vehicle("Model A400", "Peterbilt", "10,500"),
                new Vehicle("Model B500", "International", "11,000"),
                new Vehicle("Model C600", "Mack", "12,500"),
                new Vehicle("Model D700", "Western Star", "10,800"),
                new Vehicle("Model E800", "Freightliner", "11,200"),
                new Vehicle("Model F900", "Kenworth", "11,800"),
                new Vehicle("Model G1000", "Volvo", "10,200")
        );

        vehicleTable.setItems(vehicles);

        // Set up selection listener
        vehicleTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        vehicleTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                ObservableList<Vehicle> currentSelections = vehicleTable.getSelectionModel().getSelectedItems();

                // Limit to 3 selections
                if (currentSelections.size() > 3) {
                    vehicleTable.getSelectionModel().clearSelection();
                    for (int i = 0; i < 3; i++) {
                        vehicleTable.getSelectionModel().select(i);
                    }
                }

                // Update selected vehicles list
                selectedVehicles.setAll(vehicleTable.getSelectionModel().getSelectedItems());

                // Update UI
                selectionCountLabel.setText("Selected: " + selectedVehicles.size() + "/3");
                submitButton.setDisable(selectedVehicles.size() != 3);
            }
        });

        // Submit button action
        submitButton.setOnAction(event -> {
            // Process the selected vehicles
            System.out.println("Selected vehicles:");
            for (Vehicle vehicle : selectedVehicles) {
                System.out.println(vehicle);
            }

            // Show confirmation
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Submission Successful");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully selected " + selectedVehicles.size() + " vehicles.");
            alert.showAndWait();
        });
    }

    // Vehicle model class
    public static class Vehicle {
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
}