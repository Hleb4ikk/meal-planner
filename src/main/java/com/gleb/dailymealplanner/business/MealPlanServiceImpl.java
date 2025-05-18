package com.gleb.dailymealplanner.business;

import com.gleb.dailymealplanner.model.FoodProduct;
import com.gleb.dailymealplanner.model.Meal;
import com.gleb.dailymealplanner.model.MealPlan;
import com.gleb.dailymealplanner.service.MealPlanService;

import java.util.HashMap;
import java.util.Map;

public class MealPlanServiceImpl implements MealPlanService {
    private final Map<String, MealPlan> userMealPlans = new HashMap<>();

    @Override
    public void createMealPlan(String userId) {
        userMealPlans.put(userId, new MealPlan());
    }

    @Override
    public void addMeal(String userId, Meal meal) {
        userMealPlans.get(userId).addMeal(meal);
    }

    @Override
    public void removeMeal(String userId, String mealName) {
        userMealPlans.get(userId).removeMeal(mealName);
    }

    @Override
    public void addProductToMeal(String userId, String mealName, FoodProduct product, int weight) {
        userMealPlans.get(userId).getMeals().stream()
                .filter(meal -> mealName.equals(meal.getName()))
                .findFirst()
                .ifPresent(meal -> meal.addFoodItem(product, weight));
    }

    @Override
    public void removeProductFromMeal(String userId, String mealName, String productName) {
        userMealPlans.get(userId).getMeals().stream()
                .filter(meal -> mealName.equals(meal.getName()))
                .findFirst()
                .ifPresent(meal -> meal.getFoodItems().keySet()
                        .removeIf(product -> product.getName().equals(productName)));
    }

    @Override
    public MealPlan getMealPlan(String userId) {
        return userMealPlans.get(userId);
    }
}

