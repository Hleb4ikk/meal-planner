package com.gleb.dailymealplanner.service;

import com.gleb.dailymealplanner.model.MealPlan;
import com.gleb.dailymealplanner.model.UserProfile;

public interface CalorieCalculatorService {
    double calculateTotalCalories(MealPlan mealPlan);
    double calculateDailyCalorieRate(UserProfile userProfile);
}

