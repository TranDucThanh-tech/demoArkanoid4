module com.example.demoarkanoid {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires javafx.graphics;
    requires com.example.demoarkanoid;


    exports com.example.demoarkanoid4;
    opens com.example.demoarkanoid4 to javafx.fxml;

    exports com.example.demoarkanoid4.core;
    opens com.example.demoarkanoid4.core to javafx.fxml;

    exports com.example.demoarkanoid4.core.ball;
    opens com.example.demoarkanoid4.core.ball to javafx.fxml;

    exports com.example.demoarkanoid4.core.paddle;   // thêm
    opens com.example.demoarkanoid4.core.paddle to javafx.fxml; // thêm

    exports com.example.demoarkanoid4.vector2D;
    opens com.example.demoarkanoid4.vector2D;
    exports com.example.demoarkanoid4.core.brick;
    opens com.example.demoarkanoid4.core.brick to javafx.fxml;
    exports com.example.demoarkanoid4.utils;
    opens com.example.demoarkanoid4.utils;

}
