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
    exports com.example.fastfx.src.animations;
    exports com.example.fastfx.src.stylehelpers;
    exports com.example.fastfx.src.fastfxcomponents;
    exports com.example.fastfx.src.layouts;
    exports com.example.fastfx.src.responsiveness;
    exports com.example.fastfx.src.userinteraction;
    exports com.example.fastfx.src.fastfxcomponents.fdropdownlist;
}