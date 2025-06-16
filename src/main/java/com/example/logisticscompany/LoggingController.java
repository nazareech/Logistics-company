package com.example.logisticscompany;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class LoggingController extends AlertManager{
    @FXML private ImageView iconImage;

    @FXML private TextField firstNameInput;
    @FXML private TextField lastNameInput;
    @FXML private Button loginButton;

    @FXML
    private void initialize(){

        try {
            URL imageUrl = Main.class.getResource("/Img/Icon.png");
            if (imageUrl != null) {
                Image icon = new Image(imageUrl.toExternalForm());
                iconImage.setImage(icon);
            } else {
                System.err.println("Icon resource not found: " + imageUrl);
            }
        } catch (Exception e) {
            System.err.println("Failed to load icon: " + e.getMessage());
        }
    }

    @FXML
    private void logInUser(ActionEvent event){
        try {
            // Зчитуємо значення з текстових полів
            String fistName = firstNameInput.getText();
            String  lastName = lastNameInput.getText();

            // Перевіряємо, чи поля пусті
            if (fistName == null || fistName.isBlank() || lastName == null || lastName.isBlank()) {
                throw new IllegalArgumentException("Fields 'First Name' and 'Last Name' cannot be empty.");
            }

            // Встановлюємо назву користувача
            User user = User.getInstance(fistName, lastName, 0);

            String content = "We are glad to welcome you " + fistName + " " + lastName;
            showAlertInfo("Welcome", content);

            openMainMenuScene(event);

        } catch (IllegalArgumentException e){
            showAlert("Invalid Input", "Please fill in the fields 'First Name' and 'Last Name'.");
        } catch (IOException e) {
            showAlert("Error", e.getMessage());
            throw new RuntimeException(e);
        }

    }

    private void openMainMenuScene(ActionEvent event)throws IOException {
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
