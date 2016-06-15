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
public class HTMLBuilderTest {
    
    public HTMLBuilderTest() {
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
    public void testGetClosureTag() {
        String tag = "<body>";
        assertEquals("</body>", HTMLBuilder.getClosureTag(tag));
    }
    
    @Test
    public void testGenerateDocument() {
        String page = HTMLBuilder.generateDocument("", "");
        assertEquals("<!DOCTYPE html>\n<html>\n<head>\n</head>\n</body>\n</html>", page);
    }


    @Test
    public void testEncloseString() {
        String stringToEnclose = "test";
        String tag = "<body>";
        assertEquals("<body>test</body>", HTMLBuilder.encloseString(stringToEnclose, tag));
    }
    
    @Test
    public void testGenerateHTMLPageHeaderClosure() {
        assertEquals("</head>\n", HTMLBuilder.generateHTMLPageHeaderClosure());
    }
    
    @Test
    public void testGenerateHTMLPageBodyStart() {
        assertEquals("<body>\n", HTMLBuilder.generateHTMLPageBodyStart());
    }


    @Test
    public void testGenerateHeader1() {
        String text = "test";
        assertEquals("<h1>test</h1>", HTMLBuilder.generateHeader1(text));
    }


    @Test
    public void testGenerateHeader3() {
        String text = "test";
        assertEquals("<h3>test</h3>", HTMLBuilder.generateHeader3(text));
    }


    @Test
    public void testGenerateLineBreak() {
        assertEquals("<br>\n", HTMLBuilder.generateLineBreak());
    }
    
    @Test
    public void testGenerateHTMLHeader() {
        assertEquals("<!DOCTYPE html>\n<html>\n<head>\n", HTMLBuilder.generateHTMLPageHeader());
    }
    
    @Test
    public void testGenerateHTMLFooter() {
        assertEquals("</body>\n</html>", HTMLBuilder.generateHTMLPageFooter());
    }
    
}
