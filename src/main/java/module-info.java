module com.example.fastfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires jdk.jfr;
    requires javafx.base;
    requires java.sql;
    requires jdk.dynalink;
    requires java.net.http;
    requires jdk.xml.dom;
    requires jdk.compiler;
    requires jdk.unsupported;


    opens com.example.fastfx to javafx.fxml;
    exports com.example.fastfx;
    exports com.example.fastfx.src.fcomponents;
    exports com.example.fastfx.src.fcomponents.pseudoclass;
    exports com.example.fastfx.src.style.layouts;
    exports com.example.fastfx.src.simplecomponents;
}