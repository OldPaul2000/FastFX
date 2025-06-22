module com.example.fastfx2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fastfx to javafx.fxml;
    exports com.example.fastfx;
}