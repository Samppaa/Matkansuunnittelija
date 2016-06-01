/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matkansuunnittelija.filemanagement;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.matkansuunnittelija.travelplanobjects.TravelPlan;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Samuli
 */
public class FileManager {

    private final Gson jsonParser = new Gson();
    private final File jsonFile = new File(FileManager.class.getResource("/plans.json").getFile());
    private final List<TravelPlan> travelPlans;

    public FileManager() {
        travelPlans = getTravelPlansFromFile();
    }

    public TravelPlan getTravelPlan(String name) {
        for (TravelPlan p : travelPlans) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public boolean doesTravelPlanExist(String name) {
        return getTravelPlan(name) != null;
    }

    private void saveDataFile() throws IOException {
        Files.write(jsonParser.toJson(travelPlans), jsonFile, Charsets.UTF_8);
    }

    private List<TravelPlan> getTravelPlansFromFile() {
        try (Reader reader = new InputStreamReader(FileManager.class.getResourceAsStream("/plans.json"), "UTF-8")) {
            List<TravelPlan> travelPlans2 = jsonParser.fromJson(reader, new TypeToken<List<TravelPlan>>() {
                }.getType());
            return travelPlans2;
        } catch (IOException ex) {
            return null;
        }
    }

    public List<TravelPlan> getTravelPlans() {
        return travelPlans;
    }

    public void addDayPlanToTravelPlan(int index, String name) throws IOException {
        travelPlans.get(index).addNewDayPlan(name);
        saveDataFile();
    }

    public void addDayPlanToTravelPlan(String travelPlanName) throws IOException {
        TravelPlan plan = getTravelPlan(travelPlanName);
        plan.addNewDayPlan(travelPlanName);
        saveDataFile();
    }

    public void createNewTravelPlan(String name, LocalDate startDate, LocalDate endDate) throws IOException, ParseException {
        TravelPlan plan = new TravelPlan(name, startDate, endDate);
        travelPlans.add(plan);
        saveDataFile();
    }

    public void deleteTravelPlan(String travelPlanName) throws IOException {
        travelPlans.remove(getTravelPlan(travelPlanName));
        saveDataFile();
    }

    public void clearAllPlans() throws IOException {
        travelPlans.clear();
        saveDataFile();
    }

}
