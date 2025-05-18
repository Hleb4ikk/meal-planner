package com.gleb.dailymealplanner.controllers;
import com.gleb.dailymealplanner.business.MealPlanServiceImpl;
import com.gleb.dailymealplanner.model.FoodProduct;
import com.gleb.dailymealplanner.model.Meal;
import com.gleb.dailymealplanner.service.MealPlanService;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class MealPlannerController {

    private final MealPlanService mealPlanService = new MealPlanServiceImpl();
    private String userId = "defaultUser"; // Временное значение, далее заменим

    @FXML
    private ListView<String> mealListView;

    @FXML
    private TableView<FoodProduct> mealProductTable;

    @FXML
    private TableColumn<FoodProduct, String> productNameColumn;

    @FXML
    private TableColumn<FoodProduct, Integer> productWeightColumn;

    @FXML
    private TableColumn<FoodProduct, Double> productCaloriesColumn;

    @FXML
    private Slider weightSlider;

    @FXML
    public void initialize() {
        updateMealList();
        configureProductTable();
    }

    private void updateMealList() {
        mealListView.getItems().setAll(mealPlanService.getMealPlan(userId).getMeals().stream().map(Meal::getName).toList());
    }

    private void configureProductTable() {
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productWeightColumn.setCellValueFactory(new PropertyValueFactory<>("gramms"));
        productCaloriesColumn.setCellValueFactory(new PropertyValueFactory<>("calories"));
    }

    @FXML
    private void onAddProductToMeal() {
        String selectedMeal = mealListView.getSelectionModel().getSelectedItem();
        if (selectedMeal != null) {
            // Открытие окна выбора продукта (доработаем)
        }
    }

    @FXML
    private void onRemoveProductFromMeal() {
        String selectedMeal = mealListView.getSelectionModel().getSelectedItem();
        FoodProduct selectedProduct = mealProductTable.getSelectionModel().getSelectedItem();
        if (selectedMeal != null && selectedProduct != null) {
            mealPlanService.removeProductFromMeal(userId, selectedMeal, selectedProduct.getName());
            mealProductTable.getItems().remove(selectedProduct);
        }
    }
}

