module com.gleb.dailymealplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires jakarta.xml.bind;

    opens com.gleb.dailymealplanner.controllers to javafx.fxml;
    opens com.gleb.dailymealplanner.model to javafx.base, jakarta.xml.bind;

    exports com.gleb.dailymealplanner;
    exports com.gleb.dailymealplanner.controllers;
    exports com.gleb.dailymealplanner.model;
}
