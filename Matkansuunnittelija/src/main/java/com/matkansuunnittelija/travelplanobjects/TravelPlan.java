/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matkansuunnittelija.travelplanobjects;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Samuli
 */
public class TravelPlan {

    private String name;
    private DayPlan[] dayPlans;
    private String startDate;
    private String endDate;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);

    public TravelPlan() {

    }

    private void initDayPlans(LocalDate startDate, LocalDate endDate) {
        long daysBetween = DAYS.between(startDate, endDate);
        for (int i = 0; i <= daysBetween; i++) {
            this.addNewDayPlan("Päivä " + Integer.toString(i + 1));
        }
    }

    public TravelPlan(String name, LocalDate startDate, LocalDate endDate) throws DateTimeParseException {
        this.name = name;
        this.startDate = convertDateToString(startDate);
        this.endDate = convertDateToString(endDate);
        this.dayPlans = new DayPlan[0];
        initDayPlans(startDate, endDate);
    }

    public static String convertDateToString(LocalDate date) throws DateTimeParseException {
        return date.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate convertStringToDate(String string) throws DateTimeParseException {
        return LocalDate.parse(string, DATE_TIME_FORMATTER);
    }

    public LocalDate getStartDate() throws DateTimeParseException {
        return convertStringToDate(startDate);
    }

    public LocalDate getEndDate() throws DateTimeParseException {
        return convertStringToDate(endDate);
    }

    public String getName() {
        return name;
    }

    private DayPlan[] getDayPlans() {
        return dayPlans;
    }

    public List<DayPlan> getDayPlansAsList() {
        return Arrays.asList(dayPlans);
    }

    private DayPlan getDayPlan(String name) {
        for (DayPlan p : getDayPlansAsList()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }

        return null;
    }

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
