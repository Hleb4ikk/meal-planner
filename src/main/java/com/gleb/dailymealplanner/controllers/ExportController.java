package com.gleb.dailymealplanner.controllers;

import com.gleb.dailymealplanner.business.MealPlanExportServiceImpl;
import com.gleb.dailymealplanner.business.MealPlanServiceImpl;
import com.gleb.dailymealplanner.model.MealPlan;
import com.gleb.dailymealplanner.service.MealPlanExportService;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileOutputStream;

public class ExportController {

    private final MealPlanExportService mealPlanExportService = new MealPlanExportServiceImpl();
    private String userId = "defaultUser"; // Временное значение, позже заменим

    @FXML
    private ComboBox<String> formatComboBox;

    @FXML
    private void onExportMealPlan() {
        String selectedFormat = formatComboBox.getValue();
        if (selectedFormat == null) {
            System.out.println("Выберите формат файла!");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите место сохранения");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(selectedFormat, "*." + selectedFormat.toLowerCase()));

        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            exportMealPlanToFile(file, selectedFormat);
        }
    }

    private void exportMealPlanToFile(File file, String format) {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            MealPlan mealPlan = new MealPlanServiceImpl().getMealPlan(userId);
            mealPlanExportService.exportMealPlan(mealPlan, format, fos);
            System.out.println("Файл успешно экспортирован: " + file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ошибка экспорта файла.");
        }
    }
}

