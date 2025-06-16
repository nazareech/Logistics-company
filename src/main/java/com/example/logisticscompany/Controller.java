package com.example.logisticscompany;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class Controller extends AlertManager{
    @FXML
    private Label welcomeText;
    @FXML private ImageView truckImage;
    @FXML private Label experienceLabel;


    public void initialize() {
        // Ініціалізуємо користувача
        User user = User.getInstance();
        experienceLabel.setText("Experience Level: " + user.getExperienceLevel());

        Platform.runLater(() -> {
            if (truckImage.getImage() != null) {
                truckImage.setClip(roundOffImageCorners( 20, (int) truckImage.getFitWidth(), (int) truckImage.getFitHeight()));
            }
        });
    }

    private Rectangle roundOffImageCorners(int radius, int imgWidth, int imgHeight) {

        Rectangle rectangle = new Rectangle(0, 0, imgWidth, imgHeight);
        rectangle.setArcWidth(radius);
        rectangle.setArcHeight(radius);
        return rectangle;
    }


    @FXML private void openScene(ActionEvent event) throws IOException {
        Button clickedButton = (Button) event.getSource();
        String buttonId = clickedButton.getId();

        String fxmlPath = "-";
        String stylePath = "-";

        switch (buttonId){
            case "calculateVehicleWeightButton":
                fxmlPath = "/fxml_files/vehicle-selection-view.fxml";
                stylePath = "/Style/vehicle_selection_style.css";
                break;
            case "fuelForTripButton":
                fxmlPath = "/fxml_files/fuel-for-trip-view.fxml";
                stylePath = "/Style/fuel_for_trip_style.css";
                break;
            case "viewPickupConditionsButton":
                fxmlPath = "/fxml_files/pickup-conditions-checker-view.fxml";
                stylePath = "/Style/pickup_conditions_checker_style.css";
                break;
            case "exitButton":
                String content = "Thank you for using Logisti Trainer!";
                String title = "Exit";
                String header = "GoodByе";
                showAlert(title, header, content);
                break;
            case "dispatcherSalaryButton":
                fxmlPath = "/fxml_files/dispatcher-salary-calculator-view.fxml";
                stylePath = "/Style/dispatcher_salary_calculator_style.css";
                break;
            case "checkOilStatusButton":
                fxmlPath = "/fxml_files/oil-change-tracker-view.fxml";
                stylePath = "/Style/oil_change_tracker_style.css";
                break;
        }

        if(fxmlPath != "-" || stylePath != "-") {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            root.getStylesheets().add(Objects.requireNonNull(getClass().getResource(stylePath)).toExternalForm());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
}
