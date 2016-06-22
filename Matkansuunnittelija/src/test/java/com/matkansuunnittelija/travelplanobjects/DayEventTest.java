package com.matkansuunnittelija.travelplanobjects;

import java.util.Objects;
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
public class DayEventTest {
    
    public DayEventTest() {
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
    public void testHashCode() {
        DayEvent newDayEvent = new DayEvent("nimi", "22:00", "test");
        int expectedHashCode = 7 * 19 + Objects.hashCode(newDayEvent.getName());
        assertEquals(expectedHashCode, newDayEvent.hashCode());
    }


    
}
