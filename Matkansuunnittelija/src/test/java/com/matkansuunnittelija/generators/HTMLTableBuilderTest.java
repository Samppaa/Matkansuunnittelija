package com.matkansuunnittelija.generators;

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
public class HTMLTableBuilderTest {
    
    HTMLTableBuilder tableBuilder;
    
    public HTMLTableBuilderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        tableBuilder = new HTMLTableBuilder("Test 1", "Test 2");
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void testAddRowToTable() {
        String wantedString = "<table style=\"width:100%\">\n<tr>\n<th>Test 1</th>\n<th>Test 2</th>\n</tr>\n<tr>\n<td>Item 1</td>\n<td>Item 2</td>\n</tr>\n</table>";
        tableBuilder.addRowToTable("Item 1", "Item 2");
        assertEquals(wantedString, tableBuilder.generateTable());
    }


    @Test
    public void testGenerateTable() {
        String wantedString = "<table style=\"width:100%\">\n<tr>\n<th>Test 1</th>\n<th>Test 2</th>\n</tr>\n</table>";
        assertEquals(wantedString, tableBuilder.generateTable());
    }
    
}
