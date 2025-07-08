module com.example.logisticscompany {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.logisticscompany to javafx.fxml;
    exports com.example.logisticscompany;
    exports database;
    opens database to javafx.fxml;
}