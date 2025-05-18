package com.gleb.dailymealplanner.controllers;

import com.gleb.dailymealplanner.DailyMealPlanner;
import com.gleb.dailymealplanner.business.FoodCatalogServiceImpl;
import com.gleb.dailymealplanner.service.FoodCatalogService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MainController {

    private final FoodCatalogService foodCatalogService = new FoodCatalogServiceImpl();

    @FXML
    private ListView<String> categoryListView;

    @FXML
    private ListView<String> productListView;

    @FXML
    private TextField searchField;

    @FXML
    public void initialize() {
        updateCategoryList();
    }

    private void updateCategoryList() {
        categoryListView.getItems().setAll(foodCatalogService.getCatalog().keySet());
    }

    @FXML
    private void onSearchProduct() {
        String query = searchField.getText().trim();
        productListView.getItems().setAll(foodCatalogService.searchProductByName(query));
    }

    @FXML
    private void onAddCategory() {
        // Диалоговое окно для ввода названия категории
    }

    @FXML
    private void onRemoveCategory() {
        String selectedCategory = categoryListView.getSelectionModel().getSelectedItem();
        if (selectedCategory != null) {
            foodCatalogService.removeCategory(selectedCategory);
            updateCategoryList();
        }
    }

    @FXML
    private void onAddProduct() {
        // Диалоговое окно для добавления продукта
    }

    @FXML
    private void onRemoveProduct() {
        String selectedCategory = categoryListView.getSelectionModel().getSelectedItem();
        String selectedProduct = productListView.getSelectionModel().getSelectedItem();
        if (selectedCategory != null && selectedProduct != null) {
            foodCatalogService.removeProduct(selectedCategory, selectedProduct);
            productListView.getItems().remove(selectedProduct);
        }
    }

    @FXML
    private void onOpenMealPlanner() {
        DailyMealPlanner.switchScene("/view/meal-planner-view.fxml");
    }

    @FXML
    private void onOpenExportView() {
        DailyMealPlanner.switchScene("/view/export-view.fxml");
    }

    @FXML
    private void onOpenUserProfile() {
        DailyMealPlanner.switchScene("/view/user-profile-view.fxml");
    }
}

