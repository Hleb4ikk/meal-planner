package com.gleb.dailymealplanner.model;

public class Product {

    private String name;
    private int gramms;
    private double protein;
    private double fats;
    private double carbs;
    private double calories;

    public Product(String name, int gramms, double protein, double fats, double carbs, double calories) {
        this.name = name;
        this.gramms = gramms;
        this.protein = protein;
        this.fats = fats;
        this.carbs = carbs;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGramms() {
        return gramms;
    }

    public void setGramms(int gramms) {
        this.gramms = gramms;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", gramms=" + gramms +
                ", protein=" + protein +
                ", fats=" + fats +
                ", carbs=" + carbs +
                ", calories=" + calories +
                '}';
    }
}
