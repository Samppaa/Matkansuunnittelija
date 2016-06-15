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
public class CSSStyleBuilderTest {

    CSSStyleBuilder builder = new CSSStyleBuilder();

    public CSSStyleBuilderTest() {
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
    public void testAddNewValueForStyleNew() {
        builder.addNewValueForStyle("table, th, td", "border", "1px solid black");
        builder.addNewValueForStyle("th, td", "padding", "5px");
        assertEquals("<style>\ntable, th, td {\nborder: 1px solid black;\n}\nth, td {\npadding: 5px;\n}\n</style>\n", builder.generateCSS());
    }

    @Test
    public void testAddNewValueForStyleExisting() {
        builder.addNewValueForStyle("table, th, td", "border", "1px solid black");
        builder.addNewValueForStyle("table, th, td", "border-collapse", "collapse");
        assertEquals("<style>\ntable, th, td {\nborder: 1px solid black;\nborder-collapse: collapse;\n}\n</style>\n", builder.generateCSS());
    }

    @Test
    public void testGenerateCSS() {
        builder.addNewValueForStyle("table, th, td", "border", "1px solid black");
        assertEquals("<style>\ntable, th, td {\nborder: 1px solid black;\n}\n</style>\n", builder.generateCSS());
    }

}
