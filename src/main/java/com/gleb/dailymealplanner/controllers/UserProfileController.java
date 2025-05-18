package com.gleb.dailymealplanner.controllers;
import com.gleb.dailymealplanner.business.CalorieCalculatorServiceImpl;
import com.gleb.dailymealplanner.model.UserProfile;
import com.gleb.dailymealplanner.service.CalorieCalculatorService;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserProfileController {

    private final CalorieCalculatorService calorieCalculatorService = new CalorieCalculatorServiceImpl();

    @FXML
    private TextField weightField;

    @FXML
    private TextField heightField;

    @FXML
    private TextField ageField;

    @FXML
    private ComboBox<String> activityLevelComboBox;

    @FXML
    private Label caloriesLabel;

    @FXML
    private void onCalculateCalories() {
        try {
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());
            int age = Integer.parseInt(ageField.getText());
            String activityLevel = activityLevelComboBox.getValue();

            UserProfile userProfile = new UserProfile(weight, height, age, activityLevel);
            double dailyCalories = calorieCalculatorService.calculateDailyCalorieRate(userProfile);

            caloriesLabel.setText(String.format("%.2f ккал", dailyCalories));

        } catch (NumberFormatException e) {
            caloriesLabel.setText("Ошибка ввода данных");
        }
    }

    @FXML
    private void onSaveProfile() {
        // Сохранение данных пользователя (в будущем можно добавить работу с базой)
        System.out.println("Профиль сохранен!");
    }
}

