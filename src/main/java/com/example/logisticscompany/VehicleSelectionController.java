package com.example.logisticscompany;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class VehicleSelectionController {
    @FXML private TableView<Vehicle> vehicleTable;
    @FXML private TableColumn<Vehicle, String> modelColumn;
    @FXML private TableColumn<Vehicle, String> manufacturerColumn;
    @FXML private TableColumn<Vehicle, String> weightColumn;
    @FXML private Label selectionCountLabel;
    @FXML private Button submitButton;
    @FXML private Label experienceLabel; // Новий Label для відображення рівня досвіду


    private ObservableList<Vehicle> selectedVehicles = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        User user = User.getInstance();

        // Отримуємо дані із синглтон-класу VehicleManager
        VehicleManager vehicleManager = VehicleManager.getInstance();

        // Ініціалізація таблиці
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));

        // Встановлюємо дані в таблицю
        vehicleTable.setItems(vehicleManager.getVehicles());

        // Слухач для вибору
        vehicleTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        vehicleTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedVehicles.setAll(vehicleTable.getSelectionModel().getSelectedItems());
                selectionCountLabel.setText("Selected: " + selectedVehicles.size() + "/3");
                submitButton.setDisable(selectedVehicles.size() != 3);
            }
        });


        // Налаштовуємо кнопку Submit
        submitButton.setOnAction(event -> {
            displayCalculationResults();

            // Збільшуємо рівень досвіду користувача
            user.increaseExperience(10);

            // Оновлюємо Label з рівнем досвіду
            experienceLabel.setText("Experience Level: " + user.getExperienceLevel());

            System.out.println("User " + user.getName() + "'s experience level increased to: " + user.getExperienceLevel());
        });

        // Відображаємо початковий рівень досвіду
        experienceLabel.setText("Experience Level: " + user.getExperienceLevel());
    }


    /**
     * Обчислення загальної ваги і виведення результату в модальному вікні.
     */
    private void displayCalculationResults() {
        WeightCalculator calculator = new WeightCalculator();

        // Виконуємо обчислення ваги
        double totalWeight = calculator.calculateWeight(selectedVehicles);

        // Формуємо текст із результатами для виводу
        StringBuilder resultMessage = new StringBuilder();
        resultMessage.append("Selected vehicles:\n\n");

        for (Vehicle vehicle : selectedVehicles) {
            resultMessage.append(vehicle).append("\n");
        }

        resultMessage.append("\nTotal weight: ").append(totalWeight).append(" lbs");

        // Показуємо модальне вікно з результатами
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Calculation Results");
        alert.setHeaderText("Summary of Selected Vehicles");
        alert.setContentText(resultMessage.toString());
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

