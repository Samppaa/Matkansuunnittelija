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
        assertEquals(dayPlan.getName(), "Test plan 1");
    }

    @Test
    public void testAddDayEventWithSameName() {
        assertEquals(dayPlan.getDayEvents().size(), 0);
        addNewDayEvent();
        assertEquals(dayPlan.getDayEvents().size(), 1);
        addNewDayEvent();
        assertEquals(dayPlan.getDayEvents().size(), 1);
    }

    @Test
    public void testGetDayEvents() {
        dayPlan.addNewDayEvent("Test1", "13:00", "abc");
        dayPlan.addNewDayEvent("Test2", "14:00", "abc");
        List<DayEvent> events = dayPlan.getDayEvents();
        assertEquals(events.get(0).getName(), "Test1");
        assertEquals(events.get(1).getName(), "Test2");
    }

    @Test
    public void testAddNewDayEvent() {
        assertEquals(dayPlan.getDayEvents().size(), 0);
        addNewDayEvent();
        assertEquals(dayPlan.getDayEvents().size(), 1);
    }

    @Test
    public void testDeleteDayEvent() {
        addNewDayEvent();
        assertEquals(dayPlan.getDayEvents().size(), 1);
        dayPlan.deleteDayEvent("Event 1");
        assertEquals(dayPlan.getDayEvents().size(), 0);
    }

}
