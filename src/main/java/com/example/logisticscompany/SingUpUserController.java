package com.example.logisticscompany;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class SingUpUserController extends AlertManager{

    @FXML private TextField usernameInput;
    @FXML private TextField passwordInput;
    @FXML private TextField emailInput;

    @FXML private Button createAccountButton;
    @FXML private ComboBox<String> genderComboBox;
    @FXML private ComboBox<String> positionComboBox;

    private DatabaseHeandler databaseHeandler;

    @FXML void initialize() {
        databaseHeandler = new DatabaseHeandler();
        setComboBoxes();
    }

    private void setComboBoxes(){
        String[] genders = {"Male", "Female"};
        genderComboBox.getItems().addAll(genders);

        String[] positions = {"Manager", "Dispatcher", "Warehouse worker", "Customer"};
        positionComboBox.getItems().addAll(positions);
    }

    @FXML
    private void singUpUser(){
        try {
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            String email = emailInput.getText();

            String gender = genderComboBox.getValue();
            String position = "1";

            if (username.isBlank() || password.isBlank() || email.isBlank() || gender == null || position == null) {
                throw new IllegalArgumentException("Fields cannot be empty.");
            }

            databaseHeandler.singUpUser(username, password, email, position, gender);
        } catch (IllegalArgumentException e){
            showAlert("Invalid Input", "Please fill in the fields");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    private void cancelBackToLoggining(ActionEvent event) throws IOException {
        String fxmlPath = "/fxml_files/logging-view.fxml";
        String stylePath = "/Style/logging_style.css";

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();

        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource(stylePath)).toExternalForm());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
