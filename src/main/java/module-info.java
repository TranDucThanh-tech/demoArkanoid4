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

    exports com.example.demoarkanoid4;
    opens com.example.demoarkanoid4 to javafx.fxml;

    exports com.example.demoarkanoid4.model.core;
    opens com.example.demoarkanoid4.model.core to javafx.fxml;

    exports com.example.demoarkanoid4.model.core.ball;
    opens com.example.demoarkanoid4.model.core.ball to javafx.fxml;

    exports com.example.demoarkanoid4.model.core.paddle;   // thêm
    opens com.example.demoarkanoid4.model.core.paddle to javafx.fxml; // thêm

    exports com.example.demoarkanoid4.model.core.brick;
    opens com.example.demoarkanoid4.model.core.brick to javafx.fxml;
    exports com.example.demoarkanoid4.model.utils;
    opens com.example.demoarkanoid4.model.utils;

}
