package com.gleb.dailymealplanner.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Category")
public class FoodCategory {
    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "description")
    private String description;

    @XmlElement(name = "Product")
    private List<FoodProduct> products;

    public FoodCategory() {}

    public FoodCategory(String name, String description) {
        this.name = name;
        this.description = description;
        this.products = new ArrayList<>();
    }

    public FoodCategory(String name) {
        this(name, "");
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<FoodProduct> getProducts() { return products; }
}
