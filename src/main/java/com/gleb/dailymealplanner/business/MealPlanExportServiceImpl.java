package com.gleb.dailymealplanner.business;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import com.gleb.dailymealplanner.model.MealPlan;
import com.gleb.dailymealplanner.service.MealPlanExportService;

import java.io.OutputStream;

public class MealPlanExportServiceImpl implements MealPlanExportService {
    @Override
    public void exportMealPlan(MealPlan mealPlan, String format, OutputStream outputStream) {
        try {
            JAXBContext context = JAXBContext.newInstance(MealPlan.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(mealPlan, outputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
