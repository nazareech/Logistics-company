package com.example.logisticscompany;

import database.CurrentUser;
import database.DatabaseHeandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class UserProfileViewController {
    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label nickNameLabel;
    @FXML private Label positionLabel;
    @FXML private Label emailLabel;
    @FXML private Label genderLabel;

    private CurrentUser currentUser = CurrentUser.getInstance();


    @FXML private void initialize() throws SQLException, ClassNotFoundException {
        setProfileInfo();
    }


    @FXML private void cancelBackToLoggining(ActionEvent event) throws IOException {
        String fxmlPath = "/fxml_files/logging-view.fxml";
        String stylePath = "/Style/logging_style.css";

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();

        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource(stylePath)).toExternalForm());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void setProfileInfo() throws SQLException, ClassNotFoundException {

        firstNameLabel.setText(currentUser.getFirstName());
        lastNameLabel.setText(currentUser.getLastName());
        nickNameLabel.setText(currentUser.getUsername());

        positionLabel.setText(getPosition());
        emailLabel.setText(currentUser.getEmail());
        genderLabel.setText(currentUser.getGender());
    }

    private String getPosition() throws SQLException, ClassNotFoundException {

        DatabaseHeandler dbheandler = new DatabaseHeandler();
        return dbheandler.chekUserPosition(currentUser.getRoleId());



    }


}
