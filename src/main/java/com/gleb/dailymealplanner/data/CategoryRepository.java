package com.gleb.dailymealplanner.data;

import com.gleb.dailymealplanner.model.Category;
import com.gleb.dailymealplanner.model.Product;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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
    public void removeCategoryByName(String name){
        for(int i = 0; i < categories.size(); i++){
            if(categories.get(i).getName() == name){
                categories.remove(i);
            }
        }
    }
    public void editCategoryByName(String name){
        for(int i = 0; i < categories.size(); i++){
            if(categories.get(i).getName() == name){
                categories.remove(i);
            }
        }
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

    public void saveCategoriesToXml() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element rootElement = document.createElement("Categories");
            document.appendChild(rootElement);

            for (Category category : categories) {
                Element categoryElement = document.createElement("Category");
                categoryElement.setAttribute("name", category.getName());
                categoryElement.setAttribute("description", category.getDescription());

                for (Product product : category.getProducts()) {
                    Element productElement = document.createElement("Product");

                    Element name = document.createElement("Name");
                    name.appendChild(document.createTextNode(product.getName()));
                    productElement.appendChild(name);

                    Element gramms = document.createElement("Gramms");
                    gramms.appendChild(document.createTextNode(String.valueOf(product.getGramms())));
                    productElement.appendChild(gramms);

                    Element protein = document.createElement("Protein");
                    protein.appendChild(document.createTextNode(String.valueOf(product.getProtein())));
                    productElement.appendChild(protein);

                    Element fats = document.createElement("Fats");
                    fats.appendChild(document.createTextNode(String.valueOf(product.getFats())));
                    productElement.appendChild(fats);

                    Element carbs = document.createElement("Carbs");
                    carbs.appendChild(document.createTextNode(String.valueOf(product.getCarbs())));
                    productElement.appendChild(carbs);

                    Element calories = document.createElement("Calories");
                    calories.appendChild(document.createTextNode(String.valueOf(product.getCalories())));
                    productElement.appendChild(calories);

                    categoryElement.appendChild(productElement);
                }

                rootElement.appendChild(categoryElement);
            }

            // Запись в XML файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(domSource, streamResult);
            System.out.println("XML файл успешно обновлён.");

        } catch (Exception e) {
            e.printStackTrace();
        }
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
