/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matkansuunnittelija.filemanagement;

import com.matkansuunnittelija.TravelPlan;
import java.io.IOException;
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
public class FileManagerTest {
    
    FileManager manager = new FileManager();
    
    public FileManagerTest() {
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
    public void testGetTravelPlanNames() throws IOException
    {
        List<TravelPlan> plans = manager.getTravelPlans();
        assertEquals(plans.get(0).getName(), "Norway");
        assertEquals(plans.get(0).getDayPlans().length, 1);
        assertEquals(plans.get(1).getName(), "Hong Kong");
        assertEquals(plans.get(1).getDayPlans().length, 1);
        
    }
    
    @Test
    public void testGetTravelPlanDayPlans() throws IOException
    {
        List<TravelPlan> plans = manager.getTravelPlans();
        assertEquals(plans.get(0).getDayPlans()[0].getName(), "Swimming");
        assertEquals(plans.get(0).getDayPlans()[0].getDayEvents().length, 1);
        assertEquals(plans.get(1).getDayPlans()[0].getName(), "Gliding");
        assertEquals(plans.get(1).getDayPlans()[0].getDayEvents().length, 1);
    }
    
}
