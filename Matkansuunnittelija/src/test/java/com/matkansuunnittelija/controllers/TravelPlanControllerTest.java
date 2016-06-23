package com.matkansuunnittelija.controllers;

import com.matkansuunnittelija.errormanager.StatusCode;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
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
        controller = TravelPlanController.getInstance();
        controller.clearAllPlans();
        File f = new File("test.html");
        f.delete();
    }
    
    @After
    public void tearDown() throws IOException {
        controller.clearAllPlans();
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
    
    @Test
    public void testDeleteDayEventFromDayPlanSucceed() {
        addValidPlan();
        controller.addDayEventToDayPlan("Test plan", "Päivä 1", "Test event", "13:50", "Description");
        StatusCode code = controller.deleteDayEventFromDayPlan("Test plan", "Päivä 1", "Test event");
        assertEquals(StatusCode.STATUS_TRAVEL_PLAN_REMOVE_EVENT_SUCCEED, code);
    }
    
    @Test
    public void generateHTMLForTravelPlanTest() {
        controller.createNewTravelPlan("Test plan", "01.01.2012", "02.01.2012");
        controller.addDayEventToDayPlan("Test plan", "Päivä 1", "Test 1", "05:30", "Test desc");
        controller.addDayEventToDayPlan("Test plan", "Päivä 2", "Test 2", "05:35", "Test desc2");
        String wanted = "<!DOCTYPE html>\n<html>\n<head>\n<meta charset=\"UTF-8\">\n<style>\ntable, th, td {\nborder: 1px solid black;\nborder-collapse: collapse;\n}\nth, td {\npadding: 5px;\ntext-align: left;\n}\n</style>\n</head>\n<h1>Test plan 01.01.2012 - 02.01.2012</h1>\n<br>\n<h3>Päivä 1</h3>\n<table style=\"width:100%\">\n<tr>\n<th>Aika</th>\n<th>Nimi</th>\n<th>Kuvaus</th>\n</tr>\n<tr>\n<td>05:30</td>\n<td>Test 1</td>\n<td>Test desc</td>\n</tr>\n</table>\n<h3>Päivä 2</h3>\n<table style=\"width:100%\">\n<tr>\n<th>Aika</th>\n<th>Nimi</th>\n<th>Kuvaus</th>\n</tr>\n<tr>\n<td>05:35</td>\n<td>Test 2</td>\n<td>Test desc2</td>\n</tr>\n</table>\n</body>\n</html>";
        assertEquals(wanted, controller.generateHTMLForTravelPlan("Test plan"));
    }
    
    @Test
    public void isTravelPlanArchievedYesTest() {
        controller.createNewTravelPlan("Test plan", "01.01.2012", "02.01.2012");
        controller.archiveTravelPlan("Test plan");
        assertEquals(true, controller.isTravelPlanArchived("Test plan"));
    }
    
    @Test
    public void isTravelPlanArchievedNoTest() {
        controller.createNewTravelPlan("Test plan", "01.01.2012", "02.01.2012");
        assertEquals(false, controller.isTravelPlanArchived("Test plan"));
    }
    
    @Test
    public void testGenerateHTMLFileFromTravelPlan() throws IOException, ParseException {
        controller.createNewTravelPlan("Test plan", "01.01.2012", "02.01.2012");
        File f = new File("test.html");
        controller.createHTMLFileFromTravelPlan(controller.getTravelPlan("Test plan"), "test.html");
        assertEquals(true, f.exists());
        assertTrue("File size should be greater than 0", f.length() > 0);
    }
    
    @Test
    public void testGenerateHTMLFileFromTravelPlanNotEndWithHTML() throws IOException, ParseException {
        controller.createNewTravelPlan("Test plan", "01.01.2012", "02.01.2012");
        File f = new File("test.html");
        controller.createHTMLFileFromTravelPlan(controller.getTravelPlan("Test plan"), "test");
        assertEquals(true, f.exists());
        assertTrue("File size should be greater than 0", f.length() > 0);
    }
    
}
