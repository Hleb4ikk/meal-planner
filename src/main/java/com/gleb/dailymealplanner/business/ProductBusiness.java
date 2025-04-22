package com.gleb.dailymealplanner.business;

import com.gleb.dailymealplanner.data.CategoryRepository;
import com.gleb.dailymealplanner.model.Category;
import com.gleb.dailymealplanner.model.Product;
import com.gleb.dailymealplanner.service.IMealCatalogService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductBusiness implements IMealCatalogService {

    private final CategoryRepository categoryRepository;

    public ProductBusiness() {
        this.categoryRepository = new CategoryRepository();
    }

    @Override
    public void AddCategory(String categoryName) {
        if (categoryRepository.getCategoryByName(categoryName) == null) {
            Category category = new Category(categoryName, "", new ArrayList<>());
            categoryRepository.getCategories().add(category);
            categoryRepository.saveCategoriesToXml(); // ← Сохраняем изменения
        }
    }


    @Override
    public void RemoveCategory(String categoryName) {
        Category category = categoryRepository.getCategoryByName(categoryName);
        if (category != null) {
            categoryRepository.getCategories().remove(category);
        }
    }

    @Override
    public void AddProduct(String categoryName, Product product) {
        Category category = categoryRepository.getCategoryByName(categoryName);
        if (category != null) {
            category.addProduct(product);
        }
    }

    @Override
    public void RemoveProduct(String categoryName, String productName) {
        Category category = categoryRepository.getCategoryByName(categoryName);
        if (category != null) {
            List<Product> products = category.getProducts();
            products.removeIf(p -> p.getName().equalsIgnoreCase(productName));
        }
    }

    @Override
    public List<Product> SearchProductByName(String productName) {
        List<Product> result = new ArrayList<>();
        for (Category category : categoryRepository.getCategories()) {
            for (Product product : category.getProducts()) {
                if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                    result.add(product);
                }
            }
        }
        return result;
    }

    @Override
    public HashMap<String, List<Product>> GetAllCategories() {
        HashMap<String, List<Product>> result = new HashMap<>();
        for (Category category : categoryRepository.getCategories()) {
            result.put(category.getName(), new ArrayList<>(category.getProducts()));
        }
        return result;
    }
}
