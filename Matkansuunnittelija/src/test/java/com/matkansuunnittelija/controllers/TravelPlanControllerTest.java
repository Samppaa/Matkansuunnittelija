/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matkansuunnittelija.controllers;

import com.matkansuunnittelija.StatusCode;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.is;
import org.hamcrest.core.IsNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Samuli
 */
public class TravelPlanControllerTest {
    
    TravelPlanController controller;
    
    public TravelPlanControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        controller = new TravelPlanController();
        controller.clearAllPlans();
    }
    
    @After
    public void tearDown() {
    }
    
    private void addValidPlan() {
        controller.createNewTravelPlan("Test plan", "12.12.2016", "14.12.2016");
    }
    
    @Test
    public void testGetTravelPlanByNameExists() {
        addValidPlan();
        assertEquals("Test plan", controller.getTravelPlan("Test plan").getName());
    }
    
    @Test
    public void testGetTravelPlanNames() {
        controller.createNewTravelPlan("Test plan", "12.12.2016", "14.12.2016");
        controller.createNewTravelPlan("Test plan2", "12.12.2016", "14.12.2016");
        assertEquals(2, controller.getTravelPlanNames().size());
        assertEquals("Test plan2", controller.getTravelPlanNames().get(1));
    }
    
    @Test
    public void testGetTravelPlanByNameNotExist() {
        assertEquals(null, controller.getTravelPlan("Test plan"));
    }
    
    @Test
    public void testCreateNewTravelPlanSuccess() {
        StatusCode code = controller.createNewTravelPlan("Test plan", "12.12.2016", "14.12.2016");
        assertEquals(1, controller.getTravelPlans().size());
        assertEquals(StatusCode.STATUS_TRAVEL_PLAN_CREATE_SUCCEED, code);
    }
    
    @Test
    public void testCreateNewTravelPlanFailAlreadyExists() {
        addValidPlan();
        StatusCode code = controller.createNewTravelPlan("Test plan", "12.12.2016", "14.12.2016");
        assertEquals(1, controller.getTravelPlans().size());
        assertEquals(StatusCode.STATUS_TRAVEL_PLAN_CREATE_FAIL_ALREADY_EXISTS, code);
    }
    
    @Test
    public void testCreateNewTravelPlanFailDateDifferenceTooLong() {
        StatusCode code = controller.createNewTravelPlan("Test plan", "12.12.2016", "10.01.2017");
        assertEquals(0, controller.getTravelPlans().size());
        assertEquals(StatusCode.STATUS_TRAVEL_PLAN_CREATE_FAIL_DATE_DIFFERENCE_TOO_LONG, code);
    }
    
    @Test
    public void testCreateNewTravelPlanFailEndDateBeforeStartDate() {
        StatusCode code = controller.createNewTravelPlan("Test plan", "12.12.2016", "10.12.2016");
        assertEquals(0, controller.getTravelPlans().size());
        assertEquals(StatusCode.STATUS_TRAVEL_PLAN_CREATE_FAIL_START_DATE_AFTER_END_DATE, code);
    }
    
    @Test
    public void testCreateNewTravelPlanFailDateDifferentWrong() {
        StatusCode code = controller.createNewTravelPlan("Test plan", "12.12dq.2016", "10qwe.12.2016");
        assertEquals(0, controller.getTravelPlans().size());
        assertEquals(StatusCode.STATUS_TRAVREL_PLAN_CREATE_FAIL_DATE_WRONG_FORMAT, code);
    }
    
    @Test
    public void testDeleteTravelPlanSucceed() {
        addValidPlan();
        assertEquals(1, controller.getTravelPlans().size());
        controller.deleteTravelPlan("Test plan");
        assertEquals(0, controller.getTravelPlans().size());
    }
    
    @Test
    public void testAddDayEventToDayPlanSucceed() {
        addValidPlan();
        StatusCode code = controller.addDayEventToDayPlan("Test plan", "Päivä 1", "Test event", "13:50", "Description");
        assertEquals(StatusCode.STATUS_TRAVEL_PLAN_ADD_EVENT_SUCCEED, code);
        assertEquals(1, controller.getTravelPlan("Test plan").getDayPlan("Päivä 1").getDayEvents().size());
    }
    
    @Test
    public void testAddDayEventFailWrongDate() {
        addValidPlan();
        StatusCode code = controller.addDayEventToDayPlan("Test plan", "Päivä 1", "Test event", "25:00", "Description");
        assertEquals(StatusCode.STATUS_TRAVEL_PLAN_ADD_EVENT_TIME_FORMAT_WRONG_FORMAT, code);
    }
    
    @Test
    public void testAddDayEventFailAlreadyExistsWithSameTime() {
        addValidPlan();
        controller.addDayEventToDayPlan("Test plan", "Päivä 1", "Test event", "13:50", "Description");
        StatusCode code = controller.addDayEventToDayPlan("Test plan", "Päivä 1", "Test event2", "13:50", "Description2");
        assertEquals(StatusCode.STATUS_TRAVEL_PLAN_ADD_EVENT_TIME_ALREADY_EXISTS, code);
    }
    
}
