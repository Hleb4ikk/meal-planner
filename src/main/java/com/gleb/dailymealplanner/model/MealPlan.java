package com.gleb.dailymealplanner.model;

import java.util.ArrayList;
import java.util.List;

// Класс для представления плана питания
public class MealPlan {
    private List<Meal> meals;

    public MealPlan() {
        this.meals = new ArrayList<>();
    }

    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    public void removeMeal(String mealName) {
        meals.removeIf(meal -> mealName.equals(meal.getName()));
    }

    public List<Meal> getMeals() {
        return meals;
    }
}

