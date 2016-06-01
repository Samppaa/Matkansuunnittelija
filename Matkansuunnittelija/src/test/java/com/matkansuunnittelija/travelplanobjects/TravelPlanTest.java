/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matkansuunnittelija.travelplanobjects;

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
public class TravelPlanTest {

    private TravelPlan travelPlan;

    public TravelPlanTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws ParseException {
        travelPlan = new TravelPlan("Test plan", TravelPlan.convertStringToDate("01.01.2012"), TravelPlan.convertStringToDate("05.01.2012"));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSetupWorking() {
        assertEquals("Test plan", travelPlan.getName());
        assertEquals(5, travelPlan.getDayPlansAsList().size());
    }

    @Test
    public void testAddNewDayPlan() {
        travelPlan.addNewDayPlan("Test");
        assertEquals(6, travelPlan.getDayPlansAsList().size());

    }

}
