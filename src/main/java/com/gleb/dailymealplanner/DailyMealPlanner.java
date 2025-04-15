package com.gleb.dailymealplanner;

import com.gleb.dailymealplanner.business.ProductBusiness;
import com.gleb.dailymealplanner.data.CategoryRepository;
import com.gleb.dailymealplanner.model.Category;
import com.gleb.dailymealplanner.model.Product;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class DailyMealPlanner extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        ProductBusiness productBusiness = new ProductBusiness();
        System.out.println(productBusiness.getProductByName("Пиво 4.5% алкоголя"));
//        CategoryRepository categoryRepository = new CategoryRepository();
//
//        for (Category category : categoryRepository.getCategories()) {
//            System.out.println("Категория: " + category.getName() + ", Описание: " + category.getDescription());
//            for (Product product : category.getProducts()) {
//                System.out.println("   Продукт: " + product.getName() +
//                        ", Белки: " + product.getProtein() +
//                        ", Жиры: " + product.getFats() +
//                        ", Углеводы: " + product.getCarbs() +
//                        ", Калории: " + product.getCalories());
//            }
//        }

        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}