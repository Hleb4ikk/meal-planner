package com.gleb.dailymealplanner.service;

import com.gleb.dailymealplanner.model.MealPlan;

import java.io.OutputStream;

public interface MealPlanExportService {
    void exportMealPlan(MealPlan mealPlan, String format, OutputStream outputStream);
}

