package com.example.logisticscompany;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml_files/logging-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);

        //Підключаємо стилі
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Style/logging_style.css")).toExternalForm());
        stage.setTitle("Angel Express LLC");


        try {
            URL imageUrl = Main.class.getResource("/Img/Icon.png");
            if (imageUrl != null) {
                Image icon = new Image(imageUrl.toExternalForm());
                stage.getIcons().add(icon);
            } else {
                System.err.println("Icon resource not found: " + imageUrl);
            }
        } catch (Exception e) {
            System.err.println("Failed to load icon: " + e.getMessage());
        }

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}