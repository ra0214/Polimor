module com.mendoza.databases {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mendoza.databases to javafx.fxml;
    exports com.mendoza.databases;
    exports com.mendoza.databases.controllers;
    opens com.mendoza.databases.controllers to javafx.fxml;
}