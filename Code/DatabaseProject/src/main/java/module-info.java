module com.example.databaseproject {
    requires javafx.controls;
    requires javafx.fxml;
            
            requires com.dlsc.formsfx;
    requires java.sql;
    requires java.desktop;

    opens com.example.databaseproject to javafx.fxml;
    exports com.example.databaseproject;
}