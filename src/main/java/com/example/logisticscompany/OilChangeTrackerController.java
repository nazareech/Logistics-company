package com.example.logisticscompany;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class OilChangeTrackerController {

    @FXML private ComboBox<Vehicle> vehicleSelector;
    @FXML private TextField vehicleMileageInput;
    @FXML private TextField updateMileageInput;
    @FXML private Label outputLabel;
    @FXML private Label experienceLabel;
    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button checkButton;

    private final OilChangeTracker oilChangeTracker = new OilChangeTracker();

    @FXML
    public void initialize() {
        User user = User.getInstance();
        // Відображаємо початковий рівень досвіду
        experienceLabel.setText("Experience Level: " + user.getExperienceLevel());

        // Отримуємо список транспортних засобів з VehicleManager
        VehicleManager vehicleManager = VehicleManager.getInstance();

        // Заповнюємо випадаючий список транспортними засобами
        vehicleSelector.setItems(vehicleManager.getVehicles());

        // Налаштування дій кнопок
        addButton.setOnAction(event -> addVehicle());
        updateButton.setOnAction(event -> updateMileage());
        checkButton.setOnAction(event -> checkOilChange());
    }

    private void addVehicle() {
        try {
            Vehicle selectedVehicle = vehicleSelector.getValue();
            if (selectedVehicle == null) {
                outputLabel.setText("Please select a vehicle.");
                return;
            }

            double mileage = Double.parseDouble(vehicleMileageInput.getText());
            oilChangeTracker.addVehicle(selectedVehicle, mileage);
            outputLabel.setText("Vehicle added: " + selectedVehicle + " with mileage " + mileage);
            vehicleMileageInput.clear();
        } catch (NumberFormatException e) {
            outputLabel.setText("Invalid mileage value. Please enter a numeric value.");
        }
    }

    private void updateMileage() {
        try {
            Vehicle selectedVehicle = vehicleSelector.getValue();
            if (selectedVehicle == null) {
                outputLabel.setText("Please select a vehicle.");
                return;
            }

            double miles = Double.parseDouble(updateMileageInput.getText());
            oilChangeTracker.updateMileage(selectedVehicle, miles);
            outputLabel.setText("Mileage updated for vehicle: " + selectedVehicle);
            updateMileageInput.clear();
        } catch (NumberFormatException e) {
            outputLabel.setText("Invalid mileage value. Please enter a numeric value.");
        }
    }

    private void checkOilChange() {
        Vehicle selectedVehicle = vehicleSelector.getValue();
        if (selectedVehicle == null) {
            outputLabel.setText("Please select a vehicle.");
            return;
        }

        // Збільшуємо рівень досвіду користувача
        User.getInstance().increaseExperience(3);
        // Відображаємо початковий рівень досвіду
        experienceLabel.setText("Experience Level: " + User.getInstance().getExperienceLevel());

        String output = oilChangeTracker.checkOilChange(selectedVehicle);

        outputLabel.setText(output);
    }

    @FXML private void backToMenu(ActionEvent event) throws IOException {
        String fxmlPath= "/fxml_files/main-scene-view.fxml";
        String stylePath= "/Style/main_scene_style.css";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();

        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource(stylePath)).toExternalForm());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}