package com.example.logisticscompany;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FuelCalculatorController {

    @FXML private TextField milesInput;
    @FXML private TextField mpgInput;
    @FXML private Label fuelNeededLabel;
    @FXML private Label fuelCostLabel;
    @FXML private Button calculateButton;
    @FXML private Label experienceLabel;

    private final FuelCalculator fuelCalculator = new FuelCalculator();

    @FXML
    public void initialize() {

        // Налаштування дії для кнопки "Calculate Fuel"
        calculateButton.setOnAction(event -> calculateFuel());

        // Відображаємо початковий рівень досвіду
        experienceLabel.setText("Experience Level: " + User.getInstance().getExperienceLevel());
    }

    /**
     * Обробляє розрахунок палива, оновлює інтерфейс.
     */
    private void calculateFuel() {
        try {
            // Зчитуємо значення з текстових полів
            double miles = Double.parseDouble(milesInput.getText());
            double fuelEfficiency = Double.parseDouble(mpgInput.getText());

            // Виконуємо розрахунок палива за допомогою FuelCalculator
            double[] results = fuelCalculator.calculateFuel(miles, fuelEfficiency);

            // Оновлюємо відповідні Labels
            fuelNeededLabel.setText(String.format("%.2f", results[0]));
            fuelCostLabel.setText(String.format("$%.2f", results[1]));

            // Відображаємо початковий рівень досвіду
            experienceLabel.setText("Experience Level: " + User.getInstance().getExperienceLevel());
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid numeric values for miles and MPG.");
        } catch (IllegalArgumentException e) {
            showAlert("Error", e.getMessage());
        }
    }

    /**
     * Відображає вікно із повідомленням про помилку.
     * @param title Заголовок вікна.
     * @param message Текст повідомлення.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
