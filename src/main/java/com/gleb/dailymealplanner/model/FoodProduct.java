package com.gleb.dailymealplanner.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Product")
public class FoodProduct {
    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Gramms")
    private int gramms;

    @XmlElement(name = "Protein")
    private double protein;

    @XmlElement(name = "Fats")
    private double fats;

    @XmlElement(name = "Carbs")
    private double carbs;

    @XmlElement(name = "Calories")
    private double calories;

    public String getName() { return name; }
    public int getGramms() { return gramms; }
    public double getProtein() { return protein; }
    public double getFats() { return fats; }
    public double getCarbs() { return carbs; }
    public double getCalories() { return calories; }
}
