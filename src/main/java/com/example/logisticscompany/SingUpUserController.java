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

    @FXML private TextField firstNameInput;
    @FXML private TextField lastNameInput;
    @FXML private TextField usernameInput;
    @FXML private TextField passwordInput;
    @FXML private TextField emailInput;

    @FXML private Button createAccountButton;
    @FXML private ComboBox<String> genderComboBox;
    @FXML private ComboBox<String> positionComboBox;

    private DatabaseHeandler databaseHeandler;
    private String[] positionsArray;

    @FXML void initialize() {
        setComboBoxes();
    }

    private void setComboBoxes(){
        String[] genders = {"Male", "Female"};
        genderComboBox.getItems().addAll(genders);

        positionsArray = new String[]{"Manager", "Dispatcher", "Warehouse_worker", "Customer"};
        positionComboBox.getItems().addAll(positionsArray);
    }

    @FXML
    private void singNewUser() {
        databaseHeandler = new DatabaseHeandler();

        try {
            // Отримуємо значення з полів
            String firstName = firstNameInput.getText().trim();
            String lastName = lastNameInput.getText().trim();
            String username = usernameInput.getText().trim();
            String password = passwordInput.getText().trim();
            String email = emailInput.getText().trim();
            String gender = genderComboBox.getValue();
            String selectedPosition = positionComboBox.getValue();

            // Перевіряємо, чи всі поля заповнені
            if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty() || email.isEmpty() || gender == null || selectedPosition == null) {
                throw new IllegalArgumentException("Fields cannot be empty.");
            }

            // Знаходимо індекс посади (якщо потрібно)
            int positionIndex = -1;
            for (int i = 0; i < positionsArray.length; i++) {
                if (selectedPosition.equals(positionsArray[i])) {
                    positionIndex = i + 1; // +1, якщо позиції нумеруються з 1
                    break;
                }
            }

            if (positionIndex == -1) {
                throw new IllegalArgumentException("Invalid position.");
            }

            User user = new User(firstName, lastName,
                    username, password, email, positionIndex, gender, 0 );
            // Реєстрація користувача
            databaseHeandler.singUpUser(user);

            // Якщо все ОК — показуємо повідомлення про успіх
            showAlertInfo("Success", "User created successfully.");

            // Очищаємо поля
            firstNameInput.clear();
            lastNameInput.clear();
            usernameInput.clear();
            passwordInput.clear();
            emailInput.clear();
            genderComboBox.setValue(null);
            positionComboBox.setValue(null);

        } catch (IllegalArgumentException e) {
            showAlert("Input error", e.getMessage()); // Виводимо конкретну помилку
        } catch (SQLException | ClassNotFoundException e) {
            showAlert("Database error", "User registration failed: " + e.getMessage());
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
