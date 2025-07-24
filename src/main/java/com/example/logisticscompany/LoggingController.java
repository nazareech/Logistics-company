package com.example.logisticscompany;

import animations.Shake;
import database.DatabaseHeandler;
import database.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LoggingController extends AlertManager{
    @FXML private ImageView iconImage;

    @FXML private TextField usernameInput;
    @FXML private TextField passwordInput;
    @FXML private Button loginButton;
    @FXML private Button singUpButton;
    @FXML private GridPane mainGridPlane;
    @FXML private Button showProfileInfoButton;

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

        showProfileInfoButton.onActionProperty().set(event -> {
            try {
                showInformation(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    private void logInUser(ActionEvent event){
        try {
            // Зчитуємо значення з текстових полів
            String usernameInputText = usernameInput.getText();
            String  passwordInputText = passwordInput.getText();

            // Перевіряємо, чи поля пусті
            if (usernameInputText == null || usernameInputText.isBlank() || passwordInputText == null || passwordInputText.isBlank()) {
                throw new IllegalArgumentException("Fields 'First Name' and 'Last Name' cannot be empty.");
            }

            loginUser(usernameInputText, passwordInputText);

            String content = "We are glad to welcome you " + usernameInputText;
            showAlertInfo ("Welcome", content);

            openMainMenuScene(event);

            clearFields();

        } catch (IllegalArgumentException e){
            showAlert("Invalid Input", "Please fill in the fields 'First Name' and 'Last Name'.", e.getMessage());
        } catch (SQLException e) {
            showAlert("Помилка бази даних", "Помилка: " + e.getMessage());
        } catch (IOException e) {
            showAlert("Помилка", "Не вдалося відкрити головне меню: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void loginUser(String username, String password) throws SQLException, ClassNotFoundException {
        DatabaseHeandler databaseHeandler = new DatabaseHeandler();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        ResultSet resultSet = databaseHeandler.chekUsers(user);

        if (resultSet == null){
            shakeAnim();
            clearFields();

            showAlert("Invalid Input", "Uncorrected username or password. Please try again");
            throw new SQLException("Uncorect username or password. Please try again.");
        }

        int count = 0;
        while (resultSet.next()){
            count++;
        }

        if (count >= 1){
            System.out.println("User logged in successfully.");
        }else {
            shakeAnim();
            clearFields();

            throw new SQLException("No such user found.");
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

    @FXML
    private void openSingUpUser(ActionEvent event) throws IOException {
        String fxmlPath = "/fxml_files/sing-up-user-view.fxml";
        String stylePath = "/Style/sing_up_user_style.css";

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();

        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource(stylePath)).toExternalForm());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void clearFields(){
        usernameInput.clear();
        passwordInput.clear();
    }
    private void shakeAnim(){
        Shake userLoginShake = new Shake(usernameInput);
        Shake passwLoginShake = new Shake(passwordInput);
        userLoginShake.playAnim();
        passwLoginShake.playAnim();
    }

    private void showInformation(ActionEvent event) throws IOException {
        System.out.println("You are selected information of your profile");

        String fxmlPath= "/fxml_files/user-profile-view.fxml";
        String stylePath= "/Style/user_profile_style.css";

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();

        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource(stylePath)).toExternalForm());

        Stage stage = (Stage) mainGridPlane.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
