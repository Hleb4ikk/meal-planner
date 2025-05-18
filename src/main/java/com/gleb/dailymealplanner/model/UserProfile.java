package com.gleb.dailymealplanner.model;

// Класс для профиля пользователя, необходимый для расчёта суточной нормы калорий
public class UserProfile {
    private double weight;
    private double height;
    private int age;
    private String activityLevel;

    public UserProfile(double weight, double height, int age, String activityLevel) {
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.activityLevel = activityLevel;
    }

    // Геттеры и сеттеры
    public double getWeight() { return weight; }
    public double getHeight() { return height; }
    public int getAge() { return age; }
    public String getActivityLevel() { return activityLevel; }
}

