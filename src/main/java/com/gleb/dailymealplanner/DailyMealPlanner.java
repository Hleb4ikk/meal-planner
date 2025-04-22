package com.gleb.dailymealplanner;

import com.gleb.dailymealplanner.business.ProductBusiness;
import com.gleb.dailymealplanner.model.Product;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;

public class DailyMealPlanner extends Application {

    private final ProductBusiness mealService = new ProductBusiness();

    @Override
    public void start(Stage stage) {
        // UI компоненты
        ComboBox<String> categoryComboBox = new ComboBox<>();
        ListView<String> productListView = new ListView<>();
        TextArea productDetails = new TextArea();
        TextField searchField = new TextField();
        Button searchButton = new Button("Поиск");

        // Настройки компонентов
        productDetails.setEditable(false);
        productDetails.setWrapText(true);
        searchField.setPromptText("Введите название продукта");

        // Загрузка категорий
        HashMap<String, List<Product>> categories = mealService.GetAllCategories();
        categoryComboBox.getItems().addAll(categories.keySet());

        // Событие выбора категории
        categoryComboBox.setOnAction(e -> {
            String selectedCategory = categoryComboBox.getValue();
            if (selectedCategory != null) {
                productListView.getItems().clear();
                for (Product p : categories.get(selectedCategory)) {
                    productListView.getItems().add(p.getName());
                }
            }
        });

        // Событие выбора продукта
        productListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                Product product = mealService.SearchProductByName(newVal).stream()
                        .filter(p -> p.getName().equalsIgnoreCase(newVal))
                        .findFirst()
                        .orElse(null);
                if (product != null) {
                    productDetails.setText(
                            "Название: " + product.getName() + "\n" +
                                    "Граммы: " + product.getGramms() + " г\n" +
                                    "Белки: " + product.getProtein() + " г\n" +
                                    "Жиры: " + product.getFats() + " г\n" +
                                    "Углеводы: " + product.getCarbs() + " г\n" +
                                    "Калории: " + product.getCalories() + " ккал"
                    );
                }
            }
        });

        // Поиск
        searchButton.setOnAction(e -> {
            String query = searchField.getText().trim();
            if (!query.isEmpty()) {
                List<Product> results = mealService.SearchProductByName(query);
                productListView.getItems().clear();
                for (Product p : results) {
                    productListView.getItems().add(p.getName());
                }
            }
        });

        // Layout
        VBox leftPane = new VBox(10, new Label("Категория:"), categoryComboBox, new Label("Поиск:"), searchField, searchButton, new Label("Продукты:"), productListView);
        leftPane.setPadding(new Insets(10));
        leftPane.setPrefWidth(300);

        VBox rightPane = new VBox(10, new Label("Информация о продукте:"), productDetails);
        rightPane.setPadding(new Insets(10));

        HBox mainLayout = new HBox(leftPane, rightPane);

        Scene scene = new Scene(mainLayout, 900, 600);
        stage.setTitle("Планировщик питания");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
