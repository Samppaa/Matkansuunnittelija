/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matkansuunnittelija.travelplanobjects;

import java.util.List;
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
public class DayPlanTest {

    private DayPlan dayPlan;

    public DayPlanTest() {

    }

    private void addNewDayEvent() {
        dayPlan.addNewDayEvent("Event 1", "13:00", "Description");
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        dayPlan = new DayPlan("Test plan 1");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetName() {
        assertEquals("Test plan 1", dayPlan.getName());
    }

    @Test
    public void testAddDayEventWithSameName() {
        assertEquals(0, dayPlan.getDayEvents().size());
        addNewDayEvent();
        assertEquals(1, dayPlan.getDayEvents().size());
        addNewDayEvent();
        assertEquals(1, dayPlan.getDayEvents().size());
    }

    @Test
    public void testGetDayEvents() {
        dayPlan.addNewDayEvent("Test1", "13:00", "abc");
        dayPlan.addNewDayEvent("Test2", "14:00", "abc");
        List<DayEvent> events = dayPlan.getDayEvents();
        assertEquals("Test1", events.get(0).getName());
        assertEquals("Test2", events.get(1).getName());
    }

    @Test
    public void testAddNewDayEvent() {
        assertEquals(0, dayPlan.getDayEvents().size());
        addNewDayEvent();
        assertEquals(1, dayPlan.getDayEvents().size());
    }
    
    @Test
    public void testGetDayEventByNameSuccess() {
        addNewDayEvent();
        assertEquals("Event 1", dayPlan.getDayEventWithName("Event 1").getName());
    }
    
    @Test
    public void testGetDayEventByNameFail() {
        assertEquals(null, dayPlan.getDayEventWithName("Event 1"));
    }

    @Test
    public void testDeleteDayEvent() {
        addNewDayEvent();
        assertEquals(1, dayPlan.getDayEvents().size());
        dayPlan.deleteDayEvent("Event 1");
        assertEquals(0, dayPlan.getDayEvents().size());
    }
    
    @Test
    public void testDeleteDayEventNotExist() {
        addNewDayEvent();
        assertEquals(1, dayPlan.getDayEvents().size());
        dayPlan.deleteDayEvent("Event1234");
        assertEquals(1, dayPlan.getDayEvents().size());
    }

}
