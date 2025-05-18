package com.gleb.dailymealplanner.service;

import com.gleb.dailymealplanner.model.FoodProduct;
import com.gleb.dailymealplanner.model.Meal;
import com.gleb.dailymealplanner.model.MealPlan;

import java.util.List;

public interface MealPlanService {
    void createMealPlan(String userId);
    void addMeal(String userId, Meal meal);
    void removeMeal(String userId, String mealName);
    void addProductToMeal(String userId, String mealName, FoodProduct product, int weight);
    void removeProductFromMeal(String userId, String mealName, String productName);
    MealPlan getMealPlan(String userId);
}

