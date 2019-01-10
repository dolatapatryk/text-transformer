package pl.put.poznan.transformer.utils;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import pl.put.poznan.transformer.decorator.*;
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

    /**
     * Metoda sygnowana adnotacją @Before, do inicjalizowania obiektu zastępczego i
     * ustawiania zwwracanej wartości przez jego metodę <i>transform</i>
     */
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
     * Test dekoratora "upper".
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
     * Test dekoratora "lower".
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
     * Test dekoratora "inverse".
     */
    @Test
    public void testInverse() {
        String text = "MirEk";
        String expResult = "KerIm";
        
        mock = new InverseDecorator(mock);
        String result = mock.transform(text);
        assertEquals(expResult, result);
    }

    /**
     * Test dekoratora "capitalize".
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
     * Test dekoratora "addSpacesAfter" i znaku ".".
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
     * Test dekoratora "addSpacesAfter" i znaku ",".
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
     * Test dekoratora "capitalizeSentences".
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
     * Test dekoratora "abbreviationToWord".
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
     * Test dekoratora "wordToAbbreviation".
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
     * Test dekoratora "numberToText".
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
     * Test dekoratora "addDot".
     */
    @Test
    public void testAddDot() {
        String text = "Mam na imię Mariusz";
        String expResult = "Mam na imię Mariusz.";

        mock = new AddDotDecorator(mock);
        String result = mock.transform(text);
        assertEquals(expResult, result);
    }
    
    /**
     * Test dekoratora "eliminate".
     */
    @Test
    public void testEliminate() {
        String text = "Wyślij do do do mnie wiadomość";
        String expResult = "Wyślij do mnie wiadomość";

        mock = new EliminateDecorator(mock);
        String result = mock.transform(text);
        assertEquals(expResult, result);
    }
    
    /**
     * Test dekoratora "dateToText".
     */
    @Test
    public void testDateToText() {
        String text = "W tym tekscie jest data 11.12.18 fajna no nie?";
        String expResult = "W tym tekscie jest data 11 grudnia 2018 fajna no nie?";

        mock = new DateToTextDecorator(mock);
        String result = mock.transform(text);
        assertEquals(expResult, result);
    }
    
    /**
     * Test dekoratora "correctCity".
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
