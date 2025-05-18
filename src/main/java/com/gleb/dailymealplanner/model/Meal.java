package com.gleb.dailymealplanner.model;

import java.util.HashMap;
import java.util.Map;

// Класс для представления приёма пищи (завтрак, обед, ужин)
public class Meal {
    private String name;
    private Map<FoodProduct, Integer> foodItems; // Продукт и его вес (в граммах)

    public Meal(String name) {
        this.name = name;
        this.foodItems = new HashMap<>();
    }

    public void addFoodItem(FoodProduct product, int weight) {
        foodItems.put(product, weight);
    }

    public void removeFoodItem(FoodProduct product) {
        foodItems.remove(product);
    }

    public Map<FoodProduct, Integer> getFoodItems() {
        return foodItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFoodItems(Map<FoodProduct, Integer> foodItems) {
        this.foodItems = foodItems;
    }
}