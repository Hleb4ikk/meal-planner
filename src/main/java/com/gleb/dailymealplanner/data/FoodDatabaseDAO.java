package com.gleb.dailymealplanner.data;


import com.gleb.dailymealplanner.model.FoodDatabase;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

// DAO для загрузки данных из XML с использованием JAXB
public class FoodDatabaseDAO {
    private static final String XML_FILE_PATH = "src/main/resources/com/gleb/dailymealplanner/FoodProducts.xml";

    public FoodDatabase loadDatabase() {
        try {
            JAXBContext context = JAXBContext.newInstance(FoodDatabase.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (FoodDatabase) unmarshaller.unmarshal(new File(XML_FILE_PATH));
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveDatabase(FoodDatabase foodDatabase) {
        try {
            JAXBContext context = JAXBContext.newInstance(FoodDatabase.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(foodDatabase, new File(XML_FILE_PATH));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
