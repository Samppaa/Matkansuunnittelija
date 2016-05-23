/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matkansuunnittelija.filemanagement;

import com.matkansuunnittelija.travelplanobjects.TravelPlan;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
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
public class FileManagerTest {

    FileManager manager;

    public FileManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException {
        manager = new FileManager();
        manager.clearAllPlans();
        manager.createNewTravelPlan("Norway", LocalDate.of(2013, 1, 1), LocalDate.of(2013, 1, 9));
        manager.createNewTravelPlan("Hong Kong", LocalDate.of(2013, 1, 1), LocalDate.of(2013, 1, 9));
    }

    private void addTravelPlan() throws IOException {
        manager.createNewTravelPlan("Singapore", LocalDate.of(2013, 1, 1), LocalDate.of(2013, 1, 9));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testDoesTravelPlanExist() throws IOException {
        assertEquals(manager.doesTravelPlanExist("Singapore"), false);
        addTravelPlan();
        assertEquals(manager.doesTravelPlanExist("Singapore"), true);
    }

    @Test
    public void testGetTravelPlans() {
        List<TravelPlan> plans = manager.getTravelPlans();
        assertEquals(plans.size(), 2);
    }

    @Test
    public void testClearAllPlans() throws IOException {
        assertEquals(manager.getTravelPlans().size(), 2);
        manager.clearAllPlans();
        assertEquals(manager.getTravelPlans().size(), 0);
    }

    @Test
    public void addTravelPlanTest() throws IOException {
        addTravelPlan();
        List<TravelPlan> plans = manager.getTravelPlans();
        assertEquals(plans.size(), 3);
        assertEquals(plans.get(2).getName(), "Singapore");
    }

    @Test
    public void deleteAddedTravelPlanTest() throws IOException {
        addTravelPlan();
        List<TravelPlan> plans = manager.getTravelPlans();
        assertEquals(plans.get(2).getName(), "Singapore");
        manager.deleteTravelPlan("Singapore");
        assertEquals(plans.size(), 2);
        assertEquals(manager.doesTravelPlanExist("Singapore"), false);
    }

    @Test
    public void getTravelPlanTestExist() {
        assertThat(manager.getTravelPlan("Norway"), not(IsNull.nullValue()));
    }

    @Test
    public void getTravelPlanTestNotExist() {
        assertThat(manager.getTravelPlan("USA"), is(IsNull.nullValue()));
    }

}
