package com.matkansuunnittelija.generators;

import com.matkansuunnittelija.travelplanobjects.TravelPlan;
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
public class HTMLTravelPlanGeneratorTest {
    
    public HTMLTravelPlanGeneratorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void testGenerateHTMLPage() {
        TravelPlan plan = new TravelPlan("Test plan", TravelPlan.convertStringToDate("01.01.2012"), TravelPlan.convertStringToDate("02.01.2012"));
        plan.addNewDayEventToDayPlan("Päivä 1", "Test 1", "05:30", "Test desc");
        plan.addNewDayEventToDayPlan("Päivä 2", "Test 2", "05:35", "Test desc2");
        String wanted = "<!DOCTYPE html>\n<html>\n<head>\n<style>\ntable, th, td {\nborder: 1px solid black;\nborder-collapse: collapse;\n}\nth, td {\npadding: 5px;\ntext-align: left;\n}\n</style>\n</head>\n<h1>Test plan 01.01.2012 - 02.01.2012</h1>\n<br>\n<h3>Päivä 1</h3>\n<table style=\"width:100%\">\n<tr>\n<th>Aika</th>\n<th>Nimi</th>\n<th>Kuvaus</th>\n</tr>\n<tr>\n<td>05:30</td>\n<td>Test 1</td>\n<td>Test desc</td>\n</tr>\n</table>\n<h3>Päivä 2</h3>\n<table style=\"width:100%\">\n<tr>\n<th>Aika</th>\n<th>Nimi</th>\n<th>Kuvaus</th>\n</tr>\n<tr>\n<td>05:35</td>\n<td>Test 2</td>\n<td>Test desc2</td>\n</tr>\n</table>\n</body>\n</html>";
        assertEquals(wanted, HTMLTravelPlanGenerator.generateHTMLPage(plan));
    }
    
}
