package com.gleb.dailymealplanner.business;

import com.gleb.dailymealplanner.data.CategoryRepository;
import com.gleb.dailymealplanner.model.Category;
import com.gleb.dailymealplanner.model.Product;

public class ProductBusiness {
    private final CategoryRepository categoryRepository;

    public ProductBusiness() {
        this.categoryRepository = new CategoryRepository();
    }

    public Product getProductByName(String productName) {
        for (Category category : categoryRepository.getCategories()) {
            for (Product product : category.getProducts()) {
                if (product.getName().equalsIgnoreCase(productName)) {
                    return product;
                }
            }
        }
        return null;
    }
}
