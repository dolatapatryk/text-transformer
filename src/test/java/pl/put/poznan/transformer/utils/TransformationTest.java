/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.put.poznan.transformer.utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author patryk
 */
public class TransformationTest {
    
    public TransformationTest() {
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

    /**
     * Test of inverse method, of class Transformation.
     */
    @Test
    public void testInverse() {
        System.out.println("inverse");
        String text = "MirEk";
        String expResult = "KerIm";
        String result = Transformation.inverse(text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of capitalize method, of class Transformation.
     */
    @Test
    public void testCapitalize() {
        System.out.println("capitalize");
        String text = "mam fajne buty";
        String expResult = "Mam Fajne Buty";
        String result = Transformation.capitalize(text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of abbreviationToWord method, of class Transformation.
     */
    @Test
    public void testAbbreviationToWord() {
        System.out.println("abbreviationToWord");
        String src = "";
        String expResult = "";
        String result = Transformation.abbreviationToWord(src);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of wordToAbbreviation method, of class Transformation.
     */
    @Test
    public void testWordToAbbreviation() {
        System.out.println("wordToAbbreviation");
        String src = "";
        String expResult = "";
        String result = Transformation.wordToAbbreviation(src);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of expandShortcut method, of class Transformation.
     */
    @Test
    public void testExpandShortcut() {
        System.out.println("expandShortcut");
        String shortcut = "";
        boolean lastWord = false;
        String expResult = "";
        String result = Transformation.expandShortcut(shortcut, lastWord);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of createShortcut method, of class Transformation.
     */
    @Test
    public void testCreateShortcut() {
        System.out.println("createShortcut");
        String sentence = "";
        String expResult = "";
        String result = Transformation.createShortcut(sentence);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of numberToText method, of class Transformation.
     */
    @Test
    public void testNumberToText() {
        System.out.println("numberToText");
        String src = "";
        String expResult = "";
        String result = Transformation.numberToText(src);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
