package com.matkansuunnittelija.travelplanobjects;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
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
    
    @Test
    public void testConvertDateToStringSucceed() {
        LocalDate date = LocalDate.of(2010, Month.MARCH, 3);
        String convertedDate = TravelPlan.convertDateToString(date);
        assertEquals("03.03.2010", convertedDate);
    }

}
