package com.gleb.dailymealplanner.service;

import com.gleb.dailymealplanner.model.FoodProduct;

import java.util.List;
import java.util.Map;

public interface FoodCatalogService {
    void addCategory(String categoryName);
    void removeCategory(String categoryName);
    void addProduct(String categoryName, FoodProduct product);
    void removeProduct(String categoryName, String productName);
    List<String> searchProductByName(String productName);
    Map<String, List<FoodProduct>> getCatalog();
}

