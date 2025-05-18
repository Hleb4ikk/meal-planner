package com.gleb.dailymealplanner.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Db")
public class FoodDatabase {
    @XmlElement(name = "Category")
    private List<FoodCategory> categories;

    public List<FoodCategory> getCategories() { return categories; }
}
