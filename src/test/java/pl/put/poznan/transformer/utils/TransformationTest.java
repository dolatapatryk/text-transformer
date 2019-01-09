package pl.put.poznan.transformer.utils;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import pl.put.poznan.transformer.decorator.AbbreviationToWordDecorator;
import pl.put.poznan.transformer.decorator.AddSpacesAfterDecorator;
import pl.put.poznan.transformer.decorator.CapitalizeDecorator;
import pl.put.poznan.transformer.decorator.CapitalizeSentencesDecorator;
import pl.put.poznan.transformer.decorator.CorrectCityDecorator;
import pl.put.poznan.transformer.decorator.InverseDecorator;
import pl.put.poznan.transformer.decorator.LowerDecorator;
import pl.put.poznan.transformer.decorator.NumberToTextDecorator;
import pl.put.poznan.transformer.decorator.UpperDecorator;
import pl.put.poznan.transformer.decorator.WordToAbbreviationDecorator;
import pl.put.poznan.transformer.logic.Text;
import pl.put.poznan.transformer.logic.Transformer;

/**
 *
 * @author patryk, marcel, dominik, artur
 */
public class TransformationTest {
    
    /**
     * Zastępczy obiekt klasy Transformer
     */
    Transformer mock;
    
    @Before
    public void initialize() {
        mock = mock(Text.class);
        when(mock.transform(anyString())).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (String) args[0];
            }
        });
    }
    
    /**
     * Test transformacji "upper".
     */
    @Test
    public void testUpper() {
        String text = "Projekt z inżynierii oprogramowania";
        String expResult = "PROJEKT Z INŻYNIERII OPROGRAMOWANIA";
        
        mock = new UpperDecorator(mock);
        String result = mock.transform(text);
        assertEquals(expResult, result);
    }
    
    /**
     * Test transformacji "lower".
     */
    @Test
    public void testLower() {
        String text = "PROJEKT Z INŻYNIERII OPROGRAMOWANIA";
        String expResult = "projekt z inżynierii oprogramowania";
        
        mock = new LowerDecorator(mock);
        String result = mock.transform(text);
        assertEquals(expResult, result);
    }

    /**
     * Test transformacji "inverse", z klasy Transformation.
     */
    @Test
    public void testInverse() {
        String text = "MirEk";
        String expResult = "kEriM";
        
        mock = new InverseDecorator(mock);
        String result = mock.transform(text);
        assertEquals(expResult, result);
    }

    /**
     * Test transformacji "capitalize", z klasy Transformation.
     */
    @Test
    public void testCapitalize() {
        String text = "mam fajne buty";
        String expResult = "Mam Fajne Buty";
        
        mock = new CapitalizeDecorator(mock);
        String result = mock.transform(text);
        assertEquals(expResult, result);
    }

    /**
     * Test transfomacji "addSpacesAfterDots" z klasy Transformation.
     */
    @Test
    public void testAddSpacesAfterDots(){
        String text = "ala.ma.kota.";
        String expResult = "ala. ma. kota.";
        
        mock = new AddSpacesAfterDecorator(mock, '.');
        String result = mock.transform(text);
        assertEquals(expResult, result);
    }

    /**
     * Test transfomacji "addSpacesAfterCommas" z klasy Transformation.
     */
    @Test
    public void testAddSpacesAfterCommas(){
        String text = "ala,ma,kota.";
        String expResult = "ala, ma, kota.";
        
        mock = new AddSpacesAfterDecorator(mock, ',');
        String result = mock.transform(text);
        assertEquals(expResult, result);
    }

    /**
     * Test transformacji "capitalizeSentences", z klasy Transformation.
     */
    @Test
    public void testCapitalizeSentences(){
        String text = "mam fajne buty. lubię te buty. a np. czarne lubię najbardziej.";
        String expResult = "Mam fajne buty. Lubię te buty. A np. czarne lubię najbardziej.";
        
        mock = new CapitalizeSentencesDecorator(mock);
        String result = mock.transform(text);
        assertEquals(expResult, result);
    }

    /**
     * Test transformacji "abbreviationToWord", z klasy Transformation.
     */
    @Test
    public void testAbbreviationToWord() {
        String src = "Pan Prof. spóźnił się na zajęcia";
        String expResult = "Pan Profesor spóźnił się na zajęcia";
        
        mock = new AbbreviationToWordDecorator(mock);
        String result = mock.transform(src);
        assertEquals(expResult, result);
    }

    /**
     * Test transformacji "wordToAbbreviation", z klasy Transformation.
     */
    @Test
    public void testWordToAbbreviation() {
        String src = "Pieczywo to na przykład chleb i bułki";
        String expResult = "Pieczywo to np. chleb i bułki";
        
        mock = new WordToAbbreviationDecorator(mock);
        String result = mock.transform(src);
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
        
        mock = new NumberToTextDecorator(mock);
        String result = mock.transform(src);
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
    
    /**
     * Test transformacji "dateToText", z klasy Transformation.
     */
    @Test
    public void testDateToText() {
        String text = "W tym tekscie jest data 11.12.18 fajna no nie?";
        String expResult = "W tym tekscie jest data 11 grudnia 2018 fajna no nie?";
        String result = Transformation.dateToText(text);
        assertEquals(expResult, result);
    }
    
    /**
     * Test transformacji "correctCity".
     */
    @Test
    public void testCorrectCity() {
        String text = "poznan to fajne miasto tak jak wroclaw czy krakuw";
        String expResult = "Poznań to fajne miasto tak jak Wrocław czy Kraków";
        
        mock = new CorrectCityDecorator(mock);
        String result = mock.transform(text);
        assertEquals(expResult, result);
    }
}
