module com.example.fastfx2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires jdk.jfr;
    requires javafx.base;
    requires java.sql;


    opens com.example.fastfx to javafx.fxml;
    exports com.example.fastfx;
}