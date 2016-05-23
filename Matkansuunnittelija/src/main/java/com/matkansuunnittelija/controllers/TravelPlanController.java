/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matkansuunnittelija.controllers;

import com.matkansuunnittelija.filemanagement.FileManager;
import com.matkansuunnittelija.StatusCode;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Samuli
 */
public class TravelPlanController {

    public FileManager fileManager = new FileManager();
    private final int maxTravelPlanLength = 14;

    public StatusCode createNewTravelPlan(String name, LocalDate startDate, LocalDate endDate) {
        if (fileManager.doesTravelPlanExist(name)) {
            return StatusCode.STATUS_TRAVEL_PLAN_CREATE_FAIL_ALREADY_EXISTS;
        }
        if (ChronoUnit.DAYS.between(startDate, endDate) > maxTravelPlanLength) {
            return StatusCode.STATUS_TRAVEL_PLAN_CREATE_FAIL_DATE_DIFFERENCE_TOO_LONG;
        }
        if (startDate.isBefore(endDate)) {
            return StatusCode.STATUS_TRAVEL_PLAN_CREATE_FAIL_START_DATE_AFTER_END_DATE;
        }

        try {
            fileManager.createNewTravelPlan(name, startDate, endDate);
        } catch (IOException ex) {
            return StatusCode.STATUS_TRAVEL_PLAN_CREATE_FAIL_FILE_NOT_FOUND;
        }

        return StatusCode.STATUS_TRAVEL_PLAN_CREATE_SUCCEED;
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
