package com.example.logisticscompany;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class AlertManager {

    protected void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR); // Можна змінити тип на інформаційний, якщо потрібно
        alert.setTitle(title);
        alert.setHeaderText(null); // Якщо не потрібен заголовок, можна залишити значення null
        alert.setContentText(message);
        alert.showAndWait(); // Відображаємо діалогове вікно
    }
    protected void showAlertInfo(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // Можна змінити тип на інформаційний, якщо потрібно
        alert.setTitle(null);
        alert.setHeaderText(header); // Якщо не потрібен заголовок, можна залишити значення null
        alert.setContentText(message);
        alert.showAndWait(); // Відображаємо діалогове вікно
    }

    protected void showAlert(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // Можна змінити тип на інформаційний, якщо потрібно
        alert.setTitle(title);
        alert.setHeaderText(header); // Якщо не потрібен заголовок, можна залишити значення null
        alert.setContentText(message);
        alert.setOnHidden(event -> Platform.exit());
        alert.showAndWait(); // Відображаємо діалогове вікно
    }
}
