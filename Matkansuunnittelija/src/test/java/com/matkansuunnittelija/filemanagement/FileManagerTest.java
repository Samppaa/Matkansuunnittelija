package com.matkansuunnittelija.filemanagement;

import com.matkansuunnittelija.travelplanobjects.TravelPlan;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
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
    public void setUp() throws IOException, ParseException {
        manager = new FileManager();
        manager.clearAllPlans();
        manager.createNewTravelPlan("Norway", LocalDate.of(2013, 1, 1), LocalDate.of(2013, 1, 9));
        manager.createNewTravelPlan("Hong Kong", LocalDate.of(2013, 1, 1), LocalDate.of(2013, 1, 9));
        File f = new File("test.html");
        f.delete();
    }

    private void addTravelPlan() throws IOException, ParseException {
        manager.createNewTravelPlan("Singapore", LocalDate.of(2013, 1, 1), LocalDate.of(2013, 1, 9));
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void testFileManagerPlansEmptyAtStart() throws IOException {
        manager.clearAllPlans();
        FileManager manager2 = new FileManager();
        assertEquals(0, manager2.getTravelPlans().size());
    }

    @Test
    public void testDoesTravelPlanExist() throws IOException, ParseException {
        assertEquals(false, manager.doesTravelPlanExist("Singapore"));
        addTravelPlan();
        assertEquals(true, manager.doesTravelPlanExist("Singapore"));
    }

    @Test
    public void testGetTravelPlans() {
        List<TravelPlan> plans = manager.getTravelPlans();
        assertEquals(2, plans.size());
    }

    @Test
    public void testClearAllPlans() throws IOException {
        assertEquals(2, manager.getTravelPlans().size());
        manager.clearAllPlans();
        assertEquals(0, manager.getTravelPlans().size());
    }
    
    @Test
    public void testAddDayPlanToTravelPlanTestIndexAndName() throws IOException
    {
        assertEquals(9, manager.getTravelPlan("Norway").getDayPlansAsList().size());
        manager.addDayPlanToTravelPlan(0, "Test1");
        assertEquals(10, manager.getTravelPlan("Norway").getDayPlansAsList().size());
    }
    
    @Test
    public void testAddDayPlanToTravelPlanTestName() throws IOException
    {
        assertEquals(9, manager.getTravelPlan("Norway").getDayPlansAsList().size());
        manager.addDayPlanToTravelPlan("Norway");
        assertEquals(10, manager.getTravelPlan("Norway").getDayPlansAsList().size());
    }

    @Test
    public void testAddTravelPlan() throws IOException, ParseException {
        addTravelPlan();
        List<TravelPlan> plans = manager.getTravelPlans();
        assertEquals(3, plans.size());
        assertEquals("Singapore", plans.get(2).getName());
    }

    @Test
    public void testDeleteAddedTravelPlan() throws IOException, ParseException {
        addTravelPlan();
        List<TravelPlan> plans = manager.getTravelPlans();
        assertEquals("Singapore", plans.get(2).getName());
        manager.deleteTravelPlan("Singapore");
        assertEquals(2, plans.size());
        assertEquals(false, manager.doesTravelPlanExist("Singapore"));
    }

    @Test
    public void testGetTravelPlanTestExist() {
        assertThat(manager.getTravelPlan("Norway"), not(IsNull.nullValue()));
    }

    @Test
    public void testGetTravelPlanTestNotExist() {
        assertThat(manager.getTravelPlan("USA"), is(IsNull.nullValue()));
    }
    
    @Test
    public void testDeleteDayEventFromDayPlanSucceed() throws IOException, ParseException {
        addTravelPlan();
        manager.addDayEventToDayPlan("Singapore", "Päivä 1", "Event 1", "21:00", "abc");
        assertEquals(manager.getTravelPlan("Singapore").getDayPlan("Päivä 1").getDayEvents().size(), 1);
        manager.deleteDayEventFromDayPlan("Singapore", "Päivä 1", "Event 1");
        assertEquals(manager.getTravelPlan("Singapore").getDayPlan("Päivä 1").getDayEvents().size(), 0);
    }
    
    @Test
    public void testIsTravelPlanArchievedYes() throws IOException, ParseException {
        addTravelPlan();
        manager.archiveTravelPlan("Singapore");
        assertEquals(true, manager.isTravelPlanArchived("Singapore"));
    }
    
    @Test
    public void testIsTravelPlanArchievedNoTest() throws IOException, ParseException {
        addTravelPlan();
        assertEquals(false, manager.isTravelPlanArchived("Singapore"));
    }


}
