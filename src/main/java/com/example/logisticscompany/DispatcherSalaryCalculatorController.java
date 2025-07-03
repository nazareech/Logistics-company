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

public class DispatcherSalaryCalculatorController extends AlertManager{

    @FXML private Button calculateButton;
    @FXML private Label experienceLabel;

    @FXML private TextField shipmentRevenueInput;
    @FXML private TextField fullCostsInput;
    @FXML private Label dispatchersSalaryLabel;

    private final DispatcherSalaryCalculator salaryCalculator = new DispatcherSalaryCalculator();

    @FXML
    public void initialize() {
//        User user = User.getInstance();

        // Налаштування дії для кнопки "Calculate Fuel"
        calculateButton.setOnAction(event -> calculateSalary());

//        // Відображаємо початковий рівень досвіду
//        experienceLabel.setText("Experience Level: " + user.getExperienceLevel());
    }

    private void calculateSalary(){
        try {
            // Зчитуємо значення з текстових полів
            double shipmentRevenue = Double.parseDouble(shipmentRevenueInput.getText());
            double fuelCost = Double.parseDouble(fullCostsInput.getText());

            // Виконуємо розрахунок
            String results = salaryCalculator.calculateSalary(shipmentRevenue, fuelCost);

//            // Збільшуємо рівень досвіду користувача
//            User.getInstance().increaseExperience(5);
//
//            // Оновлюємо Label з рівнем досвіду
//            experienceLabel.setText("Experience Level: " + User.getInstance().getExperienceLevel());


            dispatchersSalaryLabel.setText(results);
        } catch (NumberFormatException e){
            showAlert("Invalid Input", "Please enter valid numeric values for 'Shipment Revenue' and 'Fuel Costs'.");
        } catch (IllegalArgumentException e) {
            showAlert("Error", e.getMessage());
        }
    }

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
