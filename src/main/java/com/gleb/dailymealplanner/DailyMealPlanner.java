package com.gleb.dailymealplanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class DailyMealPlanner extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        DailyMealPlanner.primaryStage = primaryStage;
        primaryStage.setTitle("Планировщик питания");
        switchScene("/view/main-view.fxml");
        primaryStage.show();
    }

    public static void switchScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(DailyMealPlanner.class.getResource(fxmlFile));
            Scene scene = new Scene(loader.load(), 800, 600);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
