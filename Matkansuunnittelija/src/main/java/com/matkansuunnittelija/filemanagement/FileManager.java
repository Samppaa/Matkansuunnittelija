package com.matkansuunnittelija.filemanagement;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.matkansuunnittelija.travelplanobjects.DayPlan;
import com.matkansuunnittelija.travelplanobjects.TravelPlan;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

/**
 * Luokka joka vastaa matkasuunnitelmien kirjoittamisesta tiedostoon ja niiden lukemisesta tiedostosta.
 * @author Samuli
 */
public class FileManager {

    private final Gson jsonParser = new Gson();
    private final File jsonFile = new File(FileManager.class.getResource("/plans.json").getFile());
    private final List<TravelPlan> travelPlans;

    public FileManager() {
        travelPlans = getTravelPlansFromFile();
    }

    /**
     * Palauttaa matkasuunnitelman jos sellainen löytyy valitulla nimellä
     * @param name
     * @return 
     */
    public TravelPlan getTravelPlan(String name) {
        for (TravelPlan p : travelPlans) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Kertoo onko valitun nimistä matkasuunnitelmaa olemassa
     * @param name
     * @return 
     */
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

    /**
     * Palauttaa kaikki matkasuunnitelmat listana
     * @return travelPlans
     */
    public List<TravelPlan> getTravelPlans() {
        return travelPlans;
    }

    /**
     * Lisää päivän matkasuunnitelmaan valitussa kohtaa
     * @param index
     * @param name
     * @throws IOException 
     */
    public void addDayPlanToTravelPlan(int index, String name) throws IOException {
        travelPlans.get(index).addNewDayPlan(name);
        saveDataFile();
    }

    /**
     * Lisää päivän matkasuunnitelmaan, jolla on valittu nimi
     * @param travelPlanName
     * @throws IOException 
     */
    public void addDayPlanToTravelPlan(String travelPlanName) throws IOException {
        TravelPlan plan = getTravelPlan(travelPlanName);
        plan.addNewDayPlan(travelPlanName);
        saveDataFile();
    }
    
    /**
     * Palauttaa onko valitun matkasuunnitelman valitulla päivällä tapahtumaa jolla on tietty nimi ja aika
     * @param travelPlanName
     * @param dayPlanName
     * @param time
     * @return true or false
     */
    public boolean dayPlanHasDayEventWithTime(String travelPlanName, String dayPlanName, String time) {
        TravelPlan plan = getTravelPlan(travelPlanName);
        DayPlan dayPlan = plan.getDayPlan(dayPlanName);
        return dayPlan.hasEventWithTime(time);
    }
    
    /**
     * Lisää tapahtuman valitun matkasuunnitelman valitulle päivälle
     * @param travelPlanName
     * @param dayPlanName
     * @param dayEventName
     * @param dayEventTime
     * @param dayEventDescription
     * @throws IOException 
     */
    public void addDayEventToDayPlan(String travelPlanName, String dayPlanName, String dayEventName, String dayEventTime, String dayEventDescription) throws IOException {
        TravelPlan plan = getTravelPlan(travelPlanName);
        plan.addNewDayEventToDayPlan(dayPlanName, dayEventName, dayEventTime, dayEventDescription);
        saveDataFile();
    }
    
    /**
     * Poistaa tapahtuman valitun matkasuunnitelman tietystä päivästä
     * @param travelPlanName
     * @param dayPlanName
     * @param dayEventName
     * @throws IOException 
     */
    public void deleteDayEventFromDayPlan(String travelPlanName, String dayPlanName, String dayEventName) throws IOException {
        TravelPlan plan = getTravelPlan(travelPlanName);
        plan.getDayPlan(dayPlanName).deleteDayEvent(dayEventName);
        saveDataFile();
    }

    /**
     * Luo uuden matkasuunnitelman valituilla tiedoilla
     * @param name
     * @param startDate
     * @param endDate
     * @throws IOException
     * @throws ParseException 
     */
    public void createNewTravelPlan(String name, LocalDate startDate, LocalDate endDate) throws IOException, ParseException {
        TravelPlan plan = new TravelPlan(name, startDate, endDate);
        travelPlans.add(plan);
        saveDataFile();
    }

    /**
     * Poistaa matkasuunnitelman tietyllä nimellä
     * @param travelPlanName
     * @throws IOException 
     */
    public void deleteTravelPlan(String travelPlanName) throws IOException {
        travelPlans.remove(getTravelPlan(travelPlanName));
        saveDataFile();
    }

    /**
     * Poistaa kaikki matkasuunnitelmat
     * @throws IOException 
     */
    public void clearAllPlans() throws IOException {
        travelPlans.clear();
        saveDataFile();
    }

}
