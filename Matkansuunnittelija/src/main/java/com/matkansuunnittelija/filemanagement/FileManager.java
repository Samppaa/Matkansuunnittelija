package com.matkansuunnittelija.filemanagement;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.matkansuunnittelija.travelplanobjects.DayPlan;
import com.matkansuunnittelija.travelplanobjects.TravelPlan;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Luokka joka vastaa matkasuunnitelmien kirjoittamisesta tiedostoon ja niiden lukemisesta tiedostosta.
 * @author Samuli
 */
public class FileManager {

    private final Gson jsonParser = new Gson();
    private List<TravelPlan> travelPlans;

    /**
     * Konstruktori joka alustaa matkasuunnitelmat tiedostosta ja luo tiedoston mikäli suunnitelmia ei löydy.
     */
    public FileManager() {
        travelPlans = getTravelPlansFromFile();
        if (travelPlans == null) {
            travelPlans = new ArrayList<>();
        }
    }

    /**
     * Palauttaa matkasuunnitelman jos sellainen löytyy valitulla nimellä.
     * @param name Haettavan matkasuunnitelman nimi
     * @return Palauttaa matkasuunnitelman jos se on olemassa, muuten palauttaa null
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
     * Kertoo onko valitun nimistä matkasuunnitelmaa olemassa.
     * @param name Haettavan matkasuunnitelman nimi
     * @return Palauttaa true jos matkasuunnitelma on olemassa, false jos ei ole
     */
    public boolean doesTravelPlanExist(String name) {
        return getTravelPlan(name) != null;
    }

    private void saveDataFile() throws IOException {
        File f = new File("plans.json");
        Files.write(jsonParser.toJson(travelPlans), f, Charsets.UTF_8);
    }

    private List<TravelPlan> getTravelPlansFromFile() {
        try (Reader reader = new InputStreamReader(new FileInputStream("plans.json"), "UTF-8")) {
            List<TravelPlan> travelPlans2 = jsonParser.fromJson(reader, new TypeToken<List<TravelPlan>>() {
                }.getType());
            return travelPlans2;
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     * Palauttaa kaikki matkasuunnitelmat listana.
     * @return travelPlans Lista matkasuunnitelmista
     */
    public List<TravelPlan> getTravelPlans() {
        return travelPlans;
    }

    /**
     * Lisää päivän matkasuunnitelmaan valitussa kohtaa.
     * @param index Kohta johon päivä lisätään matkasuunnitelmassa
     * @param name Lisättävän päivän nimi
     * @throws IOException Antaa IOException mikäli tiedoston käsittelyssä tapahtuu virhe
     */
    public void addDayPlanToTravelPlan(int index, String name) throws IOException {
        travelPlans.get(index).addNewDayPlan(name);
        saveDataFile();
    }

    /**
     * Lisää päivän matkasuunnitelmaan, jolla on valittu nimi.
     * @param travelPlanName Matkasuunnitelman, johon päivä lisään, nimi
     * @throws IOException Antaa IOException mikäli tiedoston käsittelyssä tapahtuu virhe
     */
    public void addDayPlanToTravelPlan(String travelPlanName) throws IOException {
        TravelPlan plan = getTravelPlan(travelPlanName);
        plan.addNewDayPlan(travelPlanName);
        saveDataFile();
    }
    
    /**
     * Tarkistaa onko kyseinen matkasuunnitelma arkistoitu.
     *
     * @param travelPlanName Matkasuunnitelman nimi
     * @return true tai false riippuen siitä onko matkasuunnitelma arkistoitu
     */
    public boolean isTravelPlanArchived(String travelPlanName) {
        TravelPlan plan = getTravelPlan(travelPlanName);
        return plan.isArchived();
    }
    
    /**
     * Arkistoi matkasuunnitelman.
     * @param travelPlanName Matkasuunnitelman nimi
     * @throws java.io.IOException Antaa IOException mikäli tiedoston käsittelyssä tapahtuu virhe
     */
    public void archiveTravelPlan(String travelPlanName) throws IOException {
        TravelPlan plan = getTravelPlan(travelPlanName);
        plan.archivePlan();
        saveDataFile();
    }
    
    /**
     * Palauttaa onko valitun matkasuunnitelman valitulla päivällä tapahtumaa jolla on tietty nimi ja aika.
     * @param travelPlanName Matkasuunnitelman nimi
     * @param dayPlanName Matkasuunnitelman sisässä olevan päivän nimi
     * @param time Matkasuunnitelman päivän sisällä olevan tapahtuman aika
     * @return Palauttaa true tai false riippuen siitä onko tapahtuma annetulla ajalla olemassa
     */
    public boolean dayPlanHasDayEventWithTime(String travelPlanName, String dayPlanName, String time) {
        TravelPlan plan = getTravelPlan(travelPlanName);
        DayPlan dayPlan = plan.getDayPlan(dayPlanName);
        return dayPlan.hasEventWithTime(time);
    }
    
    /**
     * Lisää tapahtuman valitun matkasuunnitelman valitulle päivälle.
     * @param travelPlanName Matkasuunnitelman nimi
     * @param dayPlanName Matkasuunnitelman sisällä olevan päivän nimi
     * @param dayEventName Lisättävän tapahtuman nimi
     * @param dayEventTime Lisättävän tapahtuman aika
     * @param dayEventDescription Lisättävän tapahtuman kuvaus
     * @throws IOException Antaa IOException mikäli tiedoston käsittelyssä tapahtuu virhe
     */
    public void addDayEventToDayPlan(String travelPlanName, String dayPlanName, String dayEventName, String dayEventTime, String dayEventDescription) throws IOException {
        TravelPlan plan = getTravelPlan(travelPlanName);
        plan.addNewDayEventToDayPlan(dayPlanName, dayEventName, dayEventTime, dayEventDescription);
        saveDataFile();
    }
    
    /**
     * Poistaa tapahtuman valitun matkasuunnitelman tietystä päivästä.
     * @param travelPlanName Matkasuunnitelman nimi
     * @param dayPlanName Matkasuunnitelman sisällä olevan päivän nimi
     * @param dayEventName Poistettavan tapahtuman nimi
     * @throws IOException Antaa IOException mikäli tiedoston käsittelyssä tapahtuu virhe
     */
    public void deleteDayEventFromDayPlan(String travelPlanName, String dayPlanName, String dayEventName) throws IOException {
        TravelPlan plan = getTravelPlan(travelPlanName);
        plan.getDayPlan(dayPlanName).deleteDayEvent(dayEventName);
        saveDataFile();
    }

    /**
     * Luo uuden matkasuunnitelman valituilla tiedoilla.
     * @param name Matkasuunnitelman nimi
     * @param startDate Matkasuunnitelman alkamispäivä
     * @param endDate Matkasuunnitelman päättymispäivä
     * @throws IOException Antaa IOException mikäli tiedoston käsittelyssä tapahtuu virhe
     * @throws ParseException Antaa ParseExceptionin mikäli päivämäärien muunnoksessa tapahtuu virhe
     */
    public void createNewTravelPlan(String name, LocalDate startDate, LocalDate endDate) throws IOException, ParseException {
        TravelPlan plan = new TravelPlan(name, startDate, endDate);
        travelPlans.add(plan);
        saveDataFile();
    }

    /**
     * Poistaa matkasuunnitelman tietyllä nimellä.
     * @param travelPlanName Matkasuunnitelman nimi
     * @throws IOException Antaa IOException mikäli tiedoston käsittelyssä tapahtuu virhe
     */
    public void deleteTravelPlan(String travelPlanName) throws IOException {
        travelPlans.remove(getTravelPlan(travelPlanName));
        saveDataFile();
    }

    /**
     * Poistaa kaikki matkasuunnitelmat.
     * @throws IOException Antaa IOException mikäli tiedoston käsittelyssä tapahtuu virhe
     */
    public void clearAllPlans() throws IOException {
        travelPlans.clear();
        saveDataFile();
    }

}
