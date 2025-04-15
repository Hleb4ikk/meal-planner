module com.gleb.dailymealplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens com.gleb.dailymealplanner to javafx.fxml;
    exports com.gleb.dailymealplanner;
}