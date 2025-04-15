package com.gleb.dailymealplanner.model;

import java.util.List;

public class Category {

    private String name;
    private String description;
    private final List<Product> list;

    public Category(String name, String description, List<Product> list) {
        this.name = name;
        this.description = description;
        this.list = list;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void addProduct(Product product){
        list.add(product);
    }
    public void removeProduct(Product product){
        list.remove(product);
    }
    public void removeProduct(int id){
        list.remove(id);
    }
    public Product getProduct(int id){
        return list.get(id);
    }
    public int size(){
        return list.size();
    }
    public List<Product> getProducts(){
        return list;
    }
}
