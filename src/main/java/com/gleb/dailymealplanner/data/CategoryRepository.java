package com.gleb.dailymealplanner.data;

import com.gleb.dailymealplanner.model.Category;
import com.gleb.dailymealplanner.model.Product;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {

    private static boolean is = false;
    private final File file;
    private List<Category> categories;

    public CategoryRepository() {
        if (!is) {
            this.file = new File("src/main/resources/com/gleb/dailymealplanner/FoodProducts.xml");
            categories = getCategoriesListFromXml();
            is = true;
        } else {
            throw new RuntimeException("ProductRepository already exists!");
        }
    }
    public List<Category> getCategories(){

        return categories;

    }

    // Метод для чтения категорий и продуктов из XML
    private List<Category> getCategoriesListFromXml() {
        List<Category> categories = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            NodeList categoryNodes = document.getElementsByTagName("Category");

            for (int i = 0; i < categoryNodes.getLength(); i++) {
                Node categoryNode = categoryNodes.item(i);

                if (categoryNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element categoryElement = (Element) categoryNode;

                    String categoryName = categoryElement.getAttribute("name");
                    String categoryDescription = categoryElement.getAttribute("description");

                    List<Product> products = new ArrayList<>();
                    NodeList productNodes = categoryElement.getElementsByTagName("Product");

                    for (int j = 0; j < productNodes.getLength(); j++) {
                        Node productNode = productNodes.item(j);

                        if (productNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element productElement = (Element) productNode;

                            String name = productElement.getElementsByTagName("Name").item(0).getTextContent();
                            int gramms = Integer.parseInt(productElement.getElementsByTagName("Gramms").item(0).getTextContent());
                            double protein = Double.parseDouble(productElement.getElementsByTagName("Protein").item(0).getTextContent().replace(",", "."));
                            double fats = Double.parseDouble(productElement.getElementsByTagName("Fats").item(0).getTextContent().replace(",", "."));
                            double carbs = Double.parseDouble(productElement.getElementsByTagName("Carbs").item(0).getTextContent().replace(",", "."));
                            double calories = Double.parseDouble(productElement.getElementsByTagName("Calories").item(0).getTextContent().replace(",", "."));

                            Product product = new Product(name, gramms, protein, fats, carbs, calories);
                            products.add(product);
                        }
                    }

                    Category category = new Category(categoryName, categoryDescription, products);
                    categories.add(category);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    public Category getCategoryByName(String name) {
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(name)) {
                return category;
            }
        }
        return null;
    }
}
