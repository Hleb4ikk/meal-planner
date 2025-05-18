package com.gleb.dailymealplanner.business;

import com.gleb.dailymealplanner.data.FoodDatabaseDAO;
import com.gleb.dailymealplanner.model.FoodCategory;
import com.gleb.dailymealplanner.model.FoodDatabase;
import com.gleb.dailymealplanner.model.FoodProduct;
import com.gleb.dailymealplanner.service.FoodCatalogService;

import java.util.*;
import java.util.stream.Collectors;

public class FoodCatalogServiceImpl implements FoodCatalogService {
    private final FoodDatabaseDAO foodDatabaseDAO;
    private final FoodDatabase foodDatabase;

    public FoodCatalogServiceImpl() {
        this.foodDatabaseDAO = new FoodDatabaseDAO();
        this.foodDatabase = foodDatabaseDAO.loadDatabase();
    }

    @Override
    public void addCategory(String categoryName) {
        foodDatabase.getCategories().add(new FoodCategory(categoryName));
        foodDatabaseDAO.saveDatabase(foodDatabase);
    }

    @Override
    public void removeCategory(String categoryName) {
        foodDatabase.getCategories().removeIf(category -> category.getName().equals(categoryName));
        foodDatabaseDAO.saveDatabase(foodDatabase);
    }

    @Override
    public void addProduct(String categoryName, FoodProduct product) {
        foodDatabase.getCategories().stream()
                .filter(category -> category.getName().equals(categoryName))
                .findFirst()
                .ifPresent(category -> category.getProducts().add(product));
        foodDatabaseDAO.saveDatabase(foodDatabase);
    }

    @Override
    public void removeProduct(String categoryName, String productName) {
        foodDatabase.getCategories().stream()
                .filter(category -> category.getName().equals(categoryName))
                .findFirst()
                .ifPresent(category -> category.getProducts().removeIf(product -> product.getName().equals(productName)));
        foodDatabaseDAO.saveDatabase(foodDatabase);
    }

    @Override
    public List<String> searchProductByName(String productName) {
        return foodDatabase.getCategories().stream()
                .flatMap(category -> category.getProducts().stream())
                .filter(product -> product.getName().toLowerCase().contains(productName.toLowerCase()))
                .map(FoodProduct::getName)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<FoodProduct>> getCatalog() {
        return foodDatabase.getCategories().stream()
                .collect(Collectors.toMap(FoodCategory::getName, FoodCategory::getProducts));
    }
}

