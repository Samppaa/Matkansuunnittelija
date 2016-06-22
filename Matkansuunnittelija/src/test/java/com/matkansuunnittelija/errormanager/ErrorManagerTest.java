/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matkansuunnittelija.errormanager;

import com.matkansuunnittelija.StatusCode;
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
public class ErrorManagerTest {
    
    public ErrorManagerTest() {
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
    public void testGetStringForErrorCodeSuccess() {
        assertEquals("Matkasuunnitelma samalla nimellä on jo olemassa", ErrorManager.getStringForErrorCode(StatusCode.STATUS_TRAVEL_PLAN_CREATE_FAIL_ALREADY_EXISTS));
    }
    
    @Test
    public void testGetStringForErrorCodeFail() {
        assertEquals(null, ErrorManager.getStringForErrorCode(StatusCode.STATUS_TRAVEL_PLAN_ADD_EVENT_SUCCEED));
    }
    
}
