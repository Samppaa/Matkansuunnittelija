/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matkansuunnittelija.travelplanobjects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public TravelPlan() {

    }

    public TravelPlan(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = convertDateToString(startDate);
        this.endDate = convertDateToString(endDate);
        this.dayPlans = new DayPlan[0];
    }

    private String convertDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD.MM.YYYY", Locale.ENGLISH);
        return date.format(formatter);
    }

    private LocalDate convertStringToDate(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD.MM.YYYY", Locale.ENGLISH);
        return LocalDate.parse(string, formatter);
    }

    public LocalDate getStartDate() {
        return convertStringToDate(startDate);
    }

    public LocalDate getEndDate() {
        return convertStringToDate(endDate);
    }

    public String getName() {
        return name;
    }

    public DayPlan[] getDayPlans() {
        return dayPlans;
    }

    private List<DayPlan> getDayPlansAsList() {
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

    public void addNewDayPlan() {
        if (dayPlans == null) {
            dayPlans = new DayPlan[0];
        }

        List<DayPlan> newDayPlans = new ArrayList<>(Arrays.asList(dayPlans));
        DayPlan plan = new DayPlan("Test");
        dayPlans = new DayPlan[newDayPlans.size()];
        newDayPlans.toArray(dayPlans);
    }

}
