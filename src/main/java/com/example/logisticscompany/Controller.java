package com.example.logisticscompany;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Controller {
    @FXML
    private Label welcomeText;
    @FXML private ImageView truckImage;


    public void initialize() {
        // Ініціалізуємо користувача
        User user = User.getInstance("John Doe", 0);

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

        String fxmlPath = "";
        String stylePath = "";

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
                break;
            case "exitButton":
                break;
            case "dispatcherSalaryButton":
                break;
            case "checkOilStatusButton":
                break;
        }


        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();

        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource(stylePath)).toExternalForm());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
