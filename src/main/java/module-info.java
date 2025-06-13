module com.example.logisticscompany {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.logisticscompany to javafx.fxml;
    exports com.example.logisticscompany;
}