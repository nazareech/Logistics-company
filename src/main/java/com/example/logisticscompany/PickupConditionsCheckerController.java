package com.example.logisticscompany;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PickupConditionsCheckerController {

    @FXML private Label experienceLabel;
    @FXML private ComboBox<String> pickupLocationSelector; // Для вибору місця
    @FXML private TextField locationInput;                // Для введення нового місця
    @FXML private TextField conditionInput;               // Для введення нового стану
    @FXML private Button addConditionButton;
    @FXML private Button getConditionButton;

    private final PickupConditionsChecker conditionsChecker = new PickupConditionsChecker();
    private final LocationAndConditionsManager locationManager = LocationAndConditionsManager.getInstance();

    @FXML
    public void initialize() {
        User user = User.getInstance();

        // Відображаємо початковий рівень досвіду
        experienceLabel.setText("Experience Level: " + user.getExperienceLevel());

        // Завантажуємо дані локацій у випадаючий список
        updatePickupLocationSelector();

        // Налаштовуємо кнопку додавання умов
        addConditionButton.setOnAction(event -> addCondition());

        // Налаштовуємо кнопку отримання умов
        getConditionButton.setOnAction(event -> getCondition());
    }

    /**
     * Оновлює випадаючий список з умовами.
     */
    private void updatePickupLocationSelector() {
        // Отримуємо список унікальних локацій
        ObservableList<String> locationNames = FXCollections.observableArrayList();
        for (LocationAndConditions locAndCond : locationManager.getLocationAndConditions()) {
            locationNames.add(locAndCond.getLocation());
        }
        pickupLocationSelector.setItems(locationNames);
    }

    /**
     * Додає або оновлює умови для місця.
     */
    private void addCondition() {
        String location = locationInput.getText().trim();
        String condition = conditionInput.getText().trim();

        if (location.isEmpty() || condition.isEmpty()) {
            showAlert("Error", "Location and condition cannot be empty.");
            return;
        }

        // Додаємо умови через PickupConditionsChecker
        conditionsChecker.setCondition(location, condition);
        showAlert("Success", "Condition added for location: " + location);

        // Очищаємо поля вводу
        locationInput.clear();
        conditionInput.clear();

        // Оновлюємо випадаючий список
        updatePickupLocationSelector();

        // Збільшуємо рівень досвіду користувача
        User.getInstance().increaseExperience(3);

        // Оновлюємо Label з рівнем досвіду
        experienceLabel.setText("Experience Level: " + User.getInstance().getExperienceLevel());

    }

    /**
     * Отримує умови для вибраного місця.
     */
    private void getCondition() {
        String selectedLocation = pickupLocationSelector.getValue();

        if (selectedLocation == null || selectedLocation.isEmpty()) {
            showAlert("Error", "Please select a location.");
            return;
        }

        // Виводимо результат через умовний клас
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Conditions for " + selectedLocation);
        alert.setHeaderText(null);

        StringBuilder message = new StringBuilder();
        for (LocationAndConditions locAndCond : locationManager.getLocationAndConditions()) {
            if (locAndCond.getLocation().equalsIgnoreCase(selectedLocation)) {
                message.append(locAndCond.getConditions());
            }
        }

        if (message.length() > 0) {
            alert.setContentText(message.toString());
        } else {
            alert.setContentText("No conditions found for this location.");
        }
        alert.showAndWait();

        // Збільшуємо рівень досвіду користувача
        User.getInstance().increaseExperience(3);

        // Оновлюємо Label з рівнем досвіду
        experienceLabel.setText("Experience Level: " + User.getInstance().getExperienceLevel());

    }

    /**
     * Відображає сповіщення користувачу.
     * @param title Заголовок сповіщення.
     * @param content Текст сповіщення.
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Повертає до головного меню.
     */
    @FXML
    private void backToMenu(ActionEvent event) throws IOException {
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