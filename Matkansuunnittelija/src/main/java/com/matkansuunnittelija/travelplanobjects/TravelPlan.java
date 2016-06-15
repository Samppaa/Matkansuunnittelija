package com.matkansuunnittelija.travelplanobjects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Matkasuunnitelma joka sisältää tietyn määrän päiviä Matkasuunnitelmalla on
 * tietty pituus ja nimi.
 *
 * @author Samuli
 */
public class TravelPlan {

    private String name;
    private DayPlan[] dayPlans;
    private String startDate;
    private String endDate;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);

    private void initDayPlans(LocalDate startDate, LocalDate endDate) {
        long daysBetween = DAYS.between(startDate, endDate);
        for (int i = 0; i <= daysBetween; i++) {
            this.addNewDayPlan("Päivä " + Integer.toString(i + 1));
        }
    }

    /**
     * Kontruktori joka määrittää matkasuunnitelman tiedot.
     *
     * @param name Matkasuunnitelman nimi
     * @param startDate Matkasuunnitelman aloituspäivämäärä
     * @param endDate Matkasuunnitelman päättymispäivämäärä
     * @throws DateTimeParseException Antaa DateTimeParseExceptionin mikäli päivämäärä objektin käsittelyssä tapahtuu virhe
     */
    public TravelPlan(String name, LocalDate startDate, LocalDate endDate) throws DateTimeParseException {
        this.name = name;
        this.startDate = convertDateToString(startDate);
        this.endDate = convertDateToString(endDate);
        this.dayPlans = new DayPlan[0];
        initDayPlans(startDate, endDate);
    }
    
    public String getStartDateAsString() {
        return startDate;
    }
    
    public String getEndDateAsString() {
        return endDate;
    }

    /**
     * Muuntaa LocalDate tyyppisen objektin String objektiksi.
     *
     * @param date Päivämäärä joka muunnetaan
     * @return LocalDate muunnettuna String objektiksi
     * @throws DateTimeParseException Antaa DateTimeParseExceptionin mikäli päivämäärä objektin käsittelyssä tapahtuu virhe
     */
    public static String convertDateToString(LocalDate date) throws DateTimeParseException {
        return date.format(DATE_TIME_FORMATTER);
    }

    /**
     * Muuttaa String objektin LocalDate objektiksi.
     *
     * @param string Päivämäärä joka muunnetaan
     * @return LocalDate Palauttaa LocalDate objektin annetun stringin perusteella
     * @throws DateTimeParseException Antaa DateTimeParseExceptionin mikäli päivämäärä objektin käsittelyssä tapahtuu virhe
     */
    public static LocalDate convertStringToDate(String string) throws DateTimeParseException {
        return LocalDate.parse(string, DATE_TIME_FORMATTER);
    }

    /**
     * Palauttaa matkasuunnitelman aloituspäivämäärän.
     *
     * @return Matkasuunnitelman aloituspäivämäärä
     * @throws DateTimeParseException Antaa DateTimeParseExceptionin mikäli päivämäärä objektin käsittelyssä tapahtuu virhe
     */
    public LocalDate getStartDate() throws DateTimeParseException {
        return convertStringToDate(startDate);
    }

    /**
     * Palauttaa matkasuunnitelman aloituspäivämäärän.
     *
     * @return Matkasuunnitelman päättymispäivämäärä
     * @throws DateTimeParseException Antaa DateTimeParseExceptionin mikäli päivämäärä objektin käsittelyssä tapahtuu virhe
     */
    public LocalDate getEndDate() throws DateTimeParseException {
        return convertStringToDate(endDate);
    }

    public String getName() {
        return name;
    }

    private DayPlan[] getDayPlans() {
        return dayPlans;
    }

    /**
     * Palauttaa matkasuunnitelman päivät listana.
     *
     * @return päivien suunnitelmat listana
     */
    public List<DayPlan> getDayPlansAsList() {
        return Arrays.asList(dayPlans);
    }

    /**
     * Palauttaa päivän tietyllä nimellä.
     *
     * @param name Päivän nimi
     * @return Päivän suunnitelma tai null jos päivää ei löydy
     */
    public DayPlan getDayPlan(String name) {
        for (DayPlan p : getDayPlansAsList()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }

        return null;
    }

    /**
     * Lisää uuden tapahtuman tietylle päivälle.
     *
     * @param dayPlanName Päivän nimi
     * @param dayEventName Tapahtuman nimi
     * @param dayEventTime Tapahtuman aika
     * @param dayEventDescription Tapahtuman kuvaus
     */
    public void addNewDayEventToDayPlan(String dayPlanName, String dayEventName, String dayEventTime, String dayEventDescription) {
        DayPlan plan = getDayPlan(dayPlanName);
        if (plan == null) {
            return;
        }
        plan.addNewDayEvent(dayEventName, dayEventTime, dayEventDescription);
    }

    /**
     * Lisää uuden päivän suunnitelmaan.
     *
     * @param name Lisättävän päivän nimi
     */
    public void addNewDayPlan(String name) {
        if (dayPlans == null) {
            dayPlans = new DayPlan[0];
        }

        List<DayPlan> newDayPlans = new ArrayList<>(Arrays.asList(dayPlans));
        DayPlan plan = new DayPlan(name);
        newDayPlans.add(plan);
        dayPlans = new DayPlan[newDayPlans.size()];
        newDayPlans.toArray(dayPlans);
    }

}
