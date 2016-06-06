/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matkansuunnittelija.controllers;

import com.matkansuunnittelija.filemanagement.FileManager;
import com.matkansuunnittelija.StatusCode;
import com.matkansuunnittelija.travelplanobjects.TravelPlan;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Samuli
 */
public class TravelPlanController {

    private final FileManager fileManager = new FileManager();
    private final int maxTravelPlanLength = 14;
    private final DateFormat format = new SimpleDateFormat("HH:mm");
    private final String regExTime = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
    private final Pattern regExPattern = Pattern.compile(regExTime);
    
    public TravelPlanController() {
        format.setLenient(false);
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
    
    public StatusCode deleteDayEventFromDayPlan(String travelPlanName, String dayPlanName, String dayEventName) {
        try {
            fileManager.deleteDayEventFromDayPlan(travelPlanName, dayPlanName, dayEventName);
        } catch (IOException ex) {
            return StatusCode.STATUS_TRAVEL_PLAN_CREATE_FAIL_FILE_NOT_FOUND;
        }
        
        return StatusCode.STATUS_TRAVEL_PLAN_REMOVE_EVENT_SUCCEED;
    }
    
    public StatusCode addDayEventToDayPlan(String travelPlanName, String dayPlanName, String dayEventName, String dayEventTime, String dayEventDescription) {
        if (!validateTimeFormat(dayEventTime)) {
            return StatusCode.STATUS_TRAVEL_PLAN_ADD_EVENT_TIME_FORMAT_WRONG_FORMAT;
        }
        if(fileManager.dayPlanHasDayEventWithTime(travelPlanName, dayPlanName, dayEventTime)) {
            return StatusCode.STATUS_TRAVEL_PLAN_ADD_EVENT_TIME_ALREADY_EXISTS;
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

    public List<TravelPlan> getTravelPlans() {
        return fileManager.getTravelPlans();
    }

    public TravelPlan getTravelPlan(String name) {
        return fileManager.getTravelPlan(name);
    }

    public void clearAllPlans() throws IOException {
        fileManager.clearAllPlans();
    }

    public ArrayList<String> getTravelPlanNames() {
        List<TravelPlan> plans = fileManager.getTravelPlans();
        ArrayList<String> names = new ArrayList<>();
        plans.stream().forEach((p) -> {
            names.add(p.getName());
        });
        return names;
    }

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

    public StatusCode createNewTravelPlan(String name, String startDate, String endDate) {

        if (!validateDateFormat(startDate) || !validateDateFormat(endDate)) {
            return StatusCode.STATUS_TRAVREL_PLAN_CREATE_FAIL_DATE_WRONG_FORMAT;
        }

        return createNewTravelPlan(name, TravelPlan.convertStringToDate(startDate), TravelPlan.convertStringToDate(endDate));
    }

    public boolean deleteTravelPlan(String name) {
        try {
            fileManager.deleteTravelPlan(name);
        } catch (IOException ex) {
            return false;
        }

        return true;
    }

}
