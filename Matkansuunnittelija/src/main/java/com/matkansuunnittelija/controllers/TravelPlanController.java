package com.matkansuunnittelija.controllers;

import com.matkansuunnittelija.filemanagement.FileManager;
import com.matkansuunnittelija.StatusCode;
import com.matkansuunnittelija.generators.HTMLTravelPlanGenerator;
import com.matkansuunnittelija.travelplanobjects.TravelPlan;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Luokka joka vastaa matkasuunnitelmien hallinnoinnista kuten lisäämisestä ja
 * poistamisesta.
 *
 * @author Samuli
 */
public class TravelPlanController {

    private final FileManager fileManager = new FileManager();
    private final int maxTravelPlanLength = 14;
    private final String regExTime = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
    private final Pattern regExPattern = Pattern.compile(regExTime);
    private static TravelPlanController travelPlanControllerSingleton = null;
    
    /**
     * Staattinen metodi, joka palauttaa singleton(ainoan) instancen luokasta.
     * @return Ainoa olemassa oleva olio TravelPlanControllerista
     */
    public static TravelPlanController getInstance() {
        if (travelPlanControllerSingleton == null) {
            travelPlanControllerSingleton = new TravelPlanController();
        }
        
        return travelPlanControllerSingleton;
    }

    /**
     * Konstruktori on protected, koska kyseessä on singleton luokka.
     */
    protected TravelPlanController() {
        
    }

    private boolean validateDateFormat(String date) {
        LocalDate date2;
        try {
            date2 = TravelPlan.convertStringToDate(date);
        } catch (DateTimeParseException ex) {
            return false;
        }

        return date2 != null;
    }

    private boolean validateTimeFormat(String time) {
        Matcher matcher = regExPattern.matcher(time);
        return matcher.matches();
    }
    
    /**
     * Luo HTML-sivun matkasuunnitelmalle.
     *
     * @param planName Matkasuunnitelman nimi
     * @return Stringin joka sisältää HTML-sivun matkasuunnitelmasta
     */
    public String generateHTMLForTravelPlan(String planName) {
        return HTMLTravelPlanGenerator.generateHTMLPage(getTravelPlan(planName));
    }

    /**
     * Poistaa tapahtuman valitun matkasuunnitelman tietystä päivästä.
     *
     * @param travelPlanName Matkasuunnitelman nimi
     * @param dayPlanName Matkasuunnitelman sisällä olevan päivän nimi
     * @param dayEventName Matkasuunnitelman päivän sisällä olevan tapahtuman nimi
     * @return StatusCode Statuskoodi joka kertoo miten tapahtuma suoriutui
     */
    public StatusCode deleteDayEventFromDayPlan(String travelPlanName, String dayPlanName, String dayEventName) {
        try {
            fileManager.deleteDayEventFromDayPlan(travelPlanName, dayPlanName, dayEventName);
        } catch (IOException ex) {
            return StatusCode.STATUS_TRAVEL_PLAN_CREATE_FAIL_FILE_NOT_FOUND;
        }

        return StatusCode.STATUS_TRAVEL_PLAN_REMOVE_EVENT_SUCCEED;
    }
    
    /**
     * Luo HTML-tiedoston matkasuunnitelmasta.
     *
     * @param plan Matkasuunnitelma
     * @param fileName Tiedoston nimi johon kirjoitetaan
     * @return true tai false riippuen onnistuuko operaatio
     */
    public boolean createHTMLFileFromTravelPlan(TravelPlan plan, String fileName) {
        try {
            fileManager.createHTMLFile(this.generateHTMLForTravelPlan(plan.getName()), fileName);
        } catch (IOException ex) {
            return false;
        }

        return true;
    }

    /**
     * Lisää tapahtuman tiedoilla valitun matkasuunnitelman tiettyyn päivään.
     *
     * @param travelPlanName Matkasuunnitelman, johon tapahtuma halutaan lisätä, nimi 
     * @param dayPlanName Matkasuunnitelman sisällä olevan päivän, johon tapahtuma lisätään, nimi
     * @param dayEventName Tapahtuman nimi
     * @param dayEventTime Tapahtuman aika
     * @param dayEventDescription Tapahtuman kuvaus
     * @return StatusCode Statuskoodi joka kertoo miten tapahtuma suoriutui
     */
    public StatusCode addDayEventToDayPlan(String travelPlanName, String dayPlanName, String dayEventName, String dayEventTime, String dayEventDescription) {
        if (!validateTimeFormat(dayEventTime)) {
            return StatusCode.STATUS_TRAVEL_PLAN_ADD_EVENT_TIME_FORMAT_WRONG_FORMAT;
        }
        if (fileManager.dayPlanHasDayEventWithTime(travelPlanName, dayPlanName, dayEventTime)) {
            return StatusCode.STATUS_TRAVEL_PLAN_ADD_EVENT_TIME_ALREADY_EXISTS;
        }
        if (dayEventName.contains("-")) {
            return StatusCode.STATUS_TRAVEL_PLAN_ADD_EVENT_ILLEGAL_CHARACTER;
        }
        try {
            fileManager.addDayEventToDayPlan(travelPlanName, dayPlanName, dayEventName, dayEventTime, dayEventDescription);
        } catch (IOException ex) {
            return StatusCode.STATUS_TRAVEL_PLAN_CREATE_FAIL_FILE_NOT_FOUND;
        }
        return StatusCode.STATUS_TRAVEL_PLAN_ADD_EVENT_SUCCEED;
    }

    private StatusCode validateTravelPlan(String name, LocalDate startDate, LocalDate endDate) {
        if (fileManager.doesTravelPlanExist(name)) {
            return StatusCode.STATUS_TRAVEL_PLAN_CREATE_FAIL_ALREADY_EXISTS;
        }
        if (ChronoUnit.DAYS.between(startDate, endDate) > maxTravelPlanLength) {
            return StatusCode.STATUS_TRAVEL_PLAN_CREATE_FAIL_DATE_DIFFERENCE_TOO_LONG;
        }
        if (endDate.isBefore(startDate)) {
            return StatusCode.STATUS_TRAVEL_PLAN_CREATE_FAIL_START_DATE_AFTER_END_DATE;
        }

        return StatusCode.STATUS_TRAVEL_PLAN_INFO_OK;
    }

    /**
     * Palauttaa kaikki matkasuunnitelmat.
     *
     * @return kaikki matkasuunnitelmat
     */
    public List<TravelPlan> getTravelPlans() {
        return fileManager.getTravelPlans();
    }

    /**
     * Palauttaa matkasuunnitelman nimellä.
     *
     * @param name Haettavan matkasuunnitelman nimi
     * @return TravelPlan Palauttaa matkasuunnitelman tai null
     */
    public TravelPlan getTravelPlan(String name) {
        return fileManager.getTravelPlan(name);
    }

    /**
     * Poistaa kaikki matkasuunnitelmat.
     *
     * @throws IOException Antaa IOException mikäli tiedoston käsittelyssä tapahtuu virhe
     */
    public void clearAllPlans() throws IOException {
        fileManager.clearAllPlans();
    }
    
    /**
     * Tarkistaa onko kyseinen matkasuunnitelma arkistoitu.
     *
     * @param travelPlanName Matkasuunnitelman nimi
     * @return true tai false riippuen siitä onko matkasuunnitelma arkistoitu
     */
    public boolean isTravelPlanArchived(String travelPlanName) {
        return fileManager.isTravelPlanArchived(travelPlanName);
    }
    
    /**
     * Arkistoi matkasuunnitelman.
     *
     * @param travelPlanName Matkasuunnitelman nimi
     * @return true tai false riippuen onnistuko operaatio
     */
    public boolean archiveTravelPlan(String travelPlanName) {
        try {
            fileManager.archiveTravelPlan(travelPlanName);
        } catch (IOException ex) {
            return false;
        }

        return true;
    }

    /**
     * Palauttaa matkasuunnitelmien nimet listana.
     *
     * @return Palauttaa listan matkasuunnitelmien nimistä
     */
    public ArrayList<String> getTravelPlanNames() {
        List<TravelPlan> plans = fileManager.getTravelPlans();
        ArrayList<String> names = new ArrayList<>();
        plans.stream().forEach((p) -> {
            names.add(p.getName());
        });
        return names;
    }

    /**
     * Luo uuden matkasuunnitelman valituilla tiedoilla.
     *
     * @param name Matkasuunnitelman nimi
     * @param startDate Matkasuunnitelman aloituspäivämäärä
     * @param endDate Matkasuunnitelman lopetuspäivämäärä
     * @return StatusCode Statuskoodi joka kertoo miten tapahtuma suoriutui
     */
    public StatusCode createNewTravelPlan(String name, LocalDate startDate, LocalDate endDate) {

        StatusCode code = validateTravelPlan(name, startDate, endDate);
        if (code != StatusCode.STATUS_TRAVEL_PLAN_INFO_OK) {
            return code;
        }

        try {
            fileManager.createNewTravelPlan(name, startDate, endDate);
        } catch (IOException | ParseException ex) {
            return StatusCode.STATUS_TRAVEL_PLAN_CREATE_FAIL_FILE_NOT_FOUND;
        }

        return StatusCode.STATUS_TRAVEL_PLAN_CREATE_SUCCEED;
    }

    /**
     * Luo uuden matkasuunnitelman valituilla tiedoilla.
     *
     * @param name Matkasuunnitelman nimi
     * @param startDate Matkasuunnitelman aloituspäivämäärä
     * @param endDate Matkasuunnitelman lopetuspäivämäärä
     * @return StatusCode Statuskoodi joka kertoo miten tapahtuma suoriutui
     */
    public StatusCode createNewTravelPlan(String name, String startDate, String endDate) {

        if (!validateDateFormat(startDate) || !validateDateFormat(endDate)) {
            return StatusCode.STATUS_TRAVREL_PLAN_CREATE_FAIL_DATE_WRONG_FORMAT;
        }

        return createNewTravelPlan(name, TravelPlan.convertStringToDate(startDate), TravelPlan.convertStringToDate(endDate));
    }

    /**
     * Poistaa matkasuunnitelman nimen perusteella.
     *
     * @param name Poistettavan matkasuunnitelman nimi
     * @return Palauttaa true tai false riippuen siitä onnistuiko poisto
     */
    public boolean deleteTravelPlan(String name) {
        try {
            fileManager.deleteTravelPlan(name);
        } catch (IOException ex) {
            return false;
        }

        return true;
    }

}
