package com.gleb.dailymealplanner.business;

import com.gleb.dailymealplanner.model.MealPlan;
import com.gleb.dailymealplanner.model.UserProfile;
import com.gleb.dailymealplanner.service.CalorieCalculatorService;

public class CalorieCalculatorServiceImpl implements CalorieCalculatorService {

    @Override
    public double calculateTotalCalories(MealPlan mealPlan) {
        return mealPlan.getMeals().stream()
                .flatMap(meal -> meal.getFoodItems().entrySet().stream())
                .mapToDouble(entry -> entry.getKey().getCalories() * entry.getValue() / 100.0)
                .sum();
    }

    @Override
    public double calculateDailyCalorieRate(UserProfile userProfile) {
        double BMR = 447.593 + (9.247 * userProfile.getWeight()) + (3.098 * userProfile.getHeight()) - (4.330 * userProfile.getAge());
        double ARM;

        switch (userProfile.getActivityLevel().toLowerCase()) {
            case "low": ARM = 1.2; break;
            case "normal": ARM = 1.375; break;
            case "average": ARM = 1.55; break;
            case "high": ARM = 1.725; break;
            default: ARM = 1.375; // Стандартное значение
        }

        return BMR * ARM;
    }
}

