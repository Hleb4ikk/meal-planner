package com.gleb.dailymealplanner.service;

import com.gleb.dailymealplanner.model.Product;

import java.util.HashMap;
import java.util.List;

public interface IMealCatalogService
{
    void AddCategory(String categoryName);
    void RemoveCategory(String categoryName);
    void AddProduct(String categoryName, Product product);
    void RemoveProduct(String categoryName, String productName);
    List<Product> SearchProductByName(String productName);
    HashMap<String, List<Product>> GetAllCategories();
}
