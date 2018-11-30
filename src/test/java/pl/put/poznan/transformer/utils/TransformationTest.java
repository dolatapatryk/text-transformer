package pl.put.poznan.transformer.utils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author patryk, marcel, dominik, artur
 */
public class TransformationTest {
    
    public TransformationTest() {
    }
    
    /**
     * Test transformacji "upper", z klasy Transformation.
     */
    @Test
    public void testUpper() {
        String text = "Projekt z inżynierii oprogramowania";
        String expResult = "PROJEKT Z INŻYNIERII OPROGRAMOWANIA";
        String result = Transformation.upper(text);
        assertEquals(expResult, result);
    }
    
    
    @Test
    public void testLower() {
        String text = "PROJEKT Z INŻYNIERII OPROGRAMOWANIA";
        String expResult = "projekt z inżynierii oprogramowania";
        String result = Transformation.lower(text);
        assertEquals(expResult, result);
    }

    /**
     * Test transformacji "inverse", z klasy Transformation.
     */
    @Test
    public void testInverse() {
        String text = "MirEk";
        String expResult = "kEriM";
        String result = Transformation.inverse(text);
        assertEquals(expResult, result);
    }

    /**
     * Test transformacji "capitalize", z klasy Transformation.
     */
    @Test
    public void testCapitalize() {
        String text = "mam fajne buty";
        String expResult = "Mam Fajne Buty";
        String result = Transformation.capitalize(text);
        assertEquals(expResult, result);
    }

    /**
     * Test transfomacji "addSpacesAfterDots" z klasy Transformation.
     */
    @Test
    public void testAddSpacesAfterDots(){
        String text = "ala.ma.kota.";
        String expResult = "ala. ma. kota.";
        String result = Transformation.addSpacesAfter(text, '.');
        assertEquals(expResult, result);
    }

    /**
     * Test transfomacji "addSpacesAfterCommas" z klasy Transformation.
     */
    @Test
    public void testAddSpacesAfterCommas(){
        String text = "ala,ma,kota.";
        String expResult = "ala, ma, kota.";
        String result = Transformation.addSpacesAfter(text, ',');
        assertEquals(expResult, result);
    }

    /**
     * Test transformacji "capitalizeSentences", z klasy Transformation.
     */
    @Test
    public void testCapitalizeSentences(){
        String text = "mam fajne buty. lubię te buty. a np. czarne lubię najbardziej.";
        String expResult = "Mam fajne buty. Lubię te buty. A np. czarne lubię najbardziej.";
        String result = Transformation.capitalizeSentences(text);
        assertEquals(expResult, result);
    }

    /**
     * Test transformacji "abbreviationToWord", z klasy Transformation.
     */
    @Test
    public void testAbbreviationToWord() {
        String src = "Pan Prof. spóźnił się na zajęcia";
        String expResult = "Pan Profesor spóźnił się na zajęcia";
        String result = Transformation.abbreviationToWord(src);
        assertEquals(expResult, result);
    }

    /**
     * Test transformacji "wordToAbbreviation", z klasy Transformation.
     */
    @Test
    public void testWordToAbbreviation() {
        String src = "Pieczywo to na przykład chleb i bułki";
        String expResult = "Pieczywo to np. chleb i bułki";
        String result = Transformation.wordToAbbreviation(src);
        assertEquals(expResult, result);
    }

    /**
     * Test metody rozwijającej skróty, z klasy Transformation.
     */
    @Test
    public void testExpandShortcut() {
        String shortcut = "itp.";
        boolean lastWord = false;
        String expResult = "i tym podobne ";
        String result = Transformation.expandShortcut(shortcut, lastWord);
        assertEquals(expResult, result);
    }

    /**
     * Test metody zwijającej słowo do skrótu, z klasy Transformation.
     */
    @Test
    public void testCreateShortcut() {
        String sentence = "między innymi";
        String expResult = "m.in.";
        String result = Transformation.createShortcut(sentence);
        assertEquals(expResult, result);
    }

    /**
     * Test transformacji "numberToText", z klasy Transformation.
     */
    @Test
    public void testNumberToText() {
        String src = "Wpłać -12,67 złotych";
        String expResult = "Wpłać minus dwanaście i sześćdziesiąt siedem setnych złotych";
        String result = Transformation.numberToText(src);
        assertEquals(expResult, result);
    }
    
    /**
     * Test transformacji "addDot", z klasy Transformation.
     */
    @Test
    public void testAddDot() {
        String text = "Mam na imię Mariusz";
        String expResult = "Mam na imię Mariusz.";
        String result = Transformation.addDot(text);
        assertEquals(expResult, result);
    }
    
    /**
     * Test transformacji "eliminate", z klasy Transformation.
     */
    @Test
    public void testEliminate() {
        String text = "Wyślij do do do mnie wiadomość";
        String expResult = "Wyślij do mnie wiadomość";
        String result = Transformation.eliminate(text);
        assertEquals(expResult, result);
    }
}
