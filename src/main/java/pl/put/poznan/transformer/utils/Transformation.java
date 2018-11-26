package pl.put.poznan.transformer.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Klasa poasiadająca metody transformacji na tekście
 * @author patryk, marcel, artur, dominik
 */
public class Transformation {
    
    private static final Logger logger = LoggerFactory.getLogger(Transformation.class);
    
    /**
     * Metoda zmieniająca wszystkie litery w tekście na wielkie
     * @param text oryginalny tekst
     * @return tekst, który wszystkie litery ma wielkie
     */
    public static String upper(String text) {
        return text.toUpperCase();
    }
    
    /**
     * Metoda zmieniająca wszystkie litery w tekście na małe
     * @param text oryginalny tekst
     * @return tekst, który wszystie litery ma małe
     */
    public static String lower(String text) {
        return text.toLowerCase();
    }

    /**
     * Metoda do odwracania tekstu z zachowaniem wielkości liter na odpowiednich pozycjach
     * @param text oryginalny tekst
     * @return odwrócony tekst z zachowanie wielkości liter na pozycjach
     */
    public static String inverse(String text) {
        String reverse = reverse(text);
        
        String inverse = keepLettersSize(text, reverse);
        
        return inverse;
    }
    
    /**
     * Metoda do odwracania tekstu
     * @param text oryginalny tekst
     * @return odwrócony tekst
     */
    private static String reverse(String text) {
        return new StringBuilder(text).reverse().toString();
    }
    
    /**
     * Metoda do zachowania wielkości liter na konkretnych pozycjach
     * @param src tekst z oryginalna wielkością liter
     * @param dest przetworzony wczesniej tekst, w którym chcemy zachowac wielkosc liter ze źródła
     * @return przetworzony tekst z zachowaniem wielkości liter ze źródła
     */
    private static String keepLettersSize(String src, String dest) {
        dest = dest.toLowerCase();
        for(int i = 0; i < src.length(); i++) {
            if(Character.isUpperCase(src.charAt(i))) {
                StringBuilder temp = new StringBuilder(dest);
                temp.setCharAt(i, Character.toUpperCase(dest.charAt(i)));
                dest = temp.toString();
            }              
        }
        
        return dest;
    }

    /**
     * Metoda zamieniająca pierwszą literę w każdym wyrazie na wielką
     * @param text - tekst wejsciowy
     * @return tekst, w którym każde słowo zaczyna się wielką literą
     */
    public static String capitalize(String text){
        String[] words = text.split(" ");
        text = "";
   
        for(int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0,1).toUpperCase() + words[i].substring(1);
            
            if(i == 0)
                text = text + words[i];
            else
                text = text + " " + words[i];
        }
        
        return text;
    }

    /***
     * Metoda znajdująca i rozwijająca skróty w tekście
     * @param src - tekst w którym należy rozwinąć skróty
     * @return tekst z rozwiniętymi skrótami
     */
    public static String abbreviationToWord(String src){
        String[] words = src.split(" ");

        String newText = "";
        String newWord = "";
        for(int i = 0; i < words.length; i++){
            if(i != words.length - 1)
                newWord = expandShortcut(words[i], false);
            else
                newWord = expandShortcut(words[i], true);
            newText += newWord;
            if(newWord.equals(words[i])){
                 logger.debug("equals: " + words[i]);
                 if(i != words.length - 1)
                    newText += " ";
            } else {
                logger.debug(newText + " notequals: " + words[i]);
            }
        }

        return newText;
    }

    /***
     * Metoda znajdująca i zamieniająca słowa na skróty
     * @param src - tekst w którym należy znaleźć i zamienić wyrazy na skróty
     * @return tekst ze słowami zwiniętymi w skróty
     */
    public static String wordToAbbreviation(String src){
        List<String> sentences = new ArrayList<String>();
        sentences.add("między innymi");
        sentences.add("na przykład");
        sentences.add("i tym podobne");
        sentences.add("i tak dalej");

        for(String sentence: sentences){
            if(src.toLowerCase().contains(sentence)){
                src = src.replace(sentence, createShortcut(sentence));
                logger.debug("znaleziono: " + sentence);
            }
        }

        return src;
    }

    /***
     * Metoda zwraca rozwinięty skrót na podstawie podanego skrótu
     * @param shortcut - skrót do rozwinięcia
     * @param lastWord - flaga czy słowo jest ostatnie w zdaniu
     * @return rozwinięty skrót
     */
    public static String expandShortcut(String shortcut, boolean lastWord){
        Map<String, String> shortcuts = new HashMap<String, String>();
        shortcuts.put("prof", "profesor");
        shortcuts.put("np", "na przykład");
        shortcuts.put("dr", "doktor");
        shortcuts.put("itd", "i tak dalej");
        shortcuts.put("itp", "i tym podobne");

        String expanded = shortcut;


        if(shortcut.contains(".")){
            shortcut = shortcut.replace(".", "");
        }
        if(shortcuts.containsKey(shortcut.toLowerCase())){
            expanded = shortcuts.get(shortcut.toLowerCase());
            String[] words = expanded.split(" ");
            expanded = "";
            String word;

            for(int i = 0; i < shortcut.length(); i++){
                if(Character.isUpperCase(shortcut.charAt(i))){
                    if(i >= words.length){
                        continue;
                    }
                    word = words[i];
                    expanded += word.substring(0, 1).toUpperCase() + word.substring(1);
                    if(!lastWord)
                        expanded += " ";
                } else {
                    if(i >= words.length){
                        continue;
                    }
                    expanded += words[i];
                    if(!lastWord)
                        expanded += " ";
                }
            }
        }
        return expanded;
    }

    /***
     * Metoda zwraca skrót na podstawie podanego wyrażenia
     * @param sentence - wyrażenie do zwinięcia
     * @return zwinięte wyrażenie
     */
    public static String createShortcut(String sentence){
        Map<String, String> sentences = new HashMap<String, String>();
        sentences.put("między innymi", "m.in.");
        sentences.put("na przykład", "np.");
        sentences.put("i tym podobne", "itp.");
        sentences.put("i tak dalej", "itd.");

        String shorted = sentence;

        if(sentences.containsKey(sentence.toLowerCase())){
            shorted = sentences.get(sentence);
        }

        return shorted;
    }

    /**
     * Metoda rozpoznająca liczby i zapisująca je za pomocą słów
     * @param src oryginalny tekst
     * @return tekst z liczbami zapisanymi jako słowa
     */
    public static String numberToText(String src) {
        StringBuilder dest=new StringBuilder("");
        StringBuilder number=new StringBuilder("");
        boolean num = false, newSent = true, fraction = false;
        for(int i = 0; i < src.length(); i++) {
            if(Character.isDigit(src.charAt(i))) {
                if(!num){
                    number=new StringBuilder("");
                    num = true;
                    if(i - 1 > -1) if(!Character.isWhitespace(i-1)) dest.append(" ");
                }
                number.append(src.charAt(i));
            } else {
                if(number.length() > 0 && i + 1 < src.length() && src.charAt(i) == ',' && Character.isDigit(src.charAt(i+1)) && !fraction) {
                    if(Integer.parseInt(number.toString()) != 0) {
                        dest.append(convertNum(Integer.parseInt(number.toString()), newSent, fraction, number.length()));
                        dest.append(" i ");
                    }
                    fraction = true;
                    num = false;
                } else {
                    if(num) {
                        num = false;
                        dest.append(convertNum(Integer.parseInt(number.toString()), newSent, fraction, number.length()));
                        if(fraction) fraction = false;
                    }
                    if(i + 1 < src.length() && src.charAt(i) == '-' && Character.isDigit(src.charAt(i+1)) && !fraction){
                        if(i - 1 > -1 && !newSent) {
                            if(Character.isWhitespace(src.charAt(i-1))) dest.append("minus");
                            else dest.append(" minus");
                        }
                        else if(i - 1 > -1 && newSent) {
                            if(Character.isWhitespace(src.charAt(i-1))) {
                                dest.append("Minus");
                                newSent = false;
                            } else if(src.charAt(i-1) == '.') {
                                dest.append(" Minus");
                                newSent = false;
                            }
                        }
                        if(i == 0) dest.append("Minus");
                    } else {
                        if(i - 1 > -1 && !Character.isWhitespace(src.charAt(i)) && src.charAt(i) != '.' && src.charAt(i) != ',')
                            if(Character.isDigit(src.charAt(i-1))) dest.append(" ");
                        dest.append(src.charAt(i));
                    }
                    if(!Character.isWhitespace(src.charAt(i))) newSent = false;
                    if(src.charAt(i) == '.') newSent = true;
                }
            }
        }
        if(num) dest.append(convertNum(Integer.parseInt(number.toString()), newSent, fraction, number.length()));
        return dest.toString();
    }

    /**
     * Metoda konwertująca liczbę na tekst
     * @param number liczba poddawana konwersji na tekst
     * @param newSentence zmienna logiczna przechowująca informację o tym, czy liczba znajduje się na początku zdania
     * @param fraction zmienna logiczna przechowująca informację o tym, czy liczba jest częścią ułamkową
     * @param length długość liczby w zapisie dziesiętnym - kluczowe przy części ułamkowej
     * @return tekst reprezentujący podaną liczbę
     */
    private static String convertNum(Integer number, Boolean newSentence, Boolean fraction, Integer length) {
        String zaduzyulamek = "!za duża ilość cyfr po przecinku! ";
        String zaduzaliczba = "!za duża liczba!";
        if(length > 20 && fraction) return zaduzyulamek;
        if(length > 21 && !fraction) return zaduzaliczba;

        String[] jednosci = { "", "jeden ", "dwa ", "trzy ", "cztery ",
                            "pięć ", "sześć ", "siedem ", "osiem ", "dziewięć ", };

        String[] ulamkowe = { "", "jedna ", "dwie ", "trzy ", "cztery ",
                            "pięć ", "sześć ", "siedem ", "osiem ", "dziewięć ", };

        String[] nastki = { "", "jedenaście ", "dwanaście ", "trzynaście ",
                            "czternaście ", "piętnaście ", "szesnaście ", "siedemnaście ",
                            "osiemnaście ", "dziewiętnaście ", };

        String[] dziesiatki = { "", "dziesięć ", "dwadzieścia ",
                            "trzydzieści ", "czterdzieści ", "pięćdziesiąt ",
                            "sześćdziesiąt ", "siedemdziesiąt ", "osiemdziesiąt ",
                            "dziewięćdziesiąt ", };

        String[] setki = { "", "sto ", "dwieście ", "trzysta ", "czterysta ",
                            "pięćset ", "sześćset ", "siedemset ", "osiemset ",
                            "dziewięćset ", };

        String[][] grupy = {{ "", "", "" },
                            { "tysiąc ", "tysiące ", "tysięcy " },
                            { "milion ", "miliony ", "milionów " },
                            { "miliard ", "miliardy ", "miliardów " },
                            { "bilion ", "biliony ", "bilionów " },
                            { "biliard ", "biliardy ", "biliardów " },
                            { "trylion ", "tryliony ", "trylionów " }, };

        String[] ulamki = { "dziesiąt", "setn", "tysięczn", "dziesięciotysięczn", "stutysięczn",
                            "milionow", "dziesięciomilionow", "stumilionow",
                            "miliardow", "dziesięciomiliardow", "stumiliardow",
                            "bilionow", "dziesięciobilionow", "stubilionow",
                            "biliardow", "dziesięciobiliardow", "stubiliardow",
                            "trylionow", "dziesięciotrylionow", "stutrylionow", };

        StringBuilder word=new StringBuilder("");
        StringBuilder koncowkaUlamka=new StringBuilder(ulamki[length-1]);

        Integer j = 0/* jedności */, n = 0/* nastki */, d = 0/* dziesiątki */, s = 0/* setki */, g = 0/* grupy */, k = 0/* końcówki */;

        if (number == 0) {
            word.append("zero ");
            koncowkaUlamka.append("ych ");
        } else if(number == 1) {
            if(fraction) word.append("jedna ");
                else word.append("jeden ");
            koncowkaUlamka.append("a ");
        }
        while (number > 1) {
            s = number % 1000 / 100;
            d = number % 100 / 10;
            j = number % 10;
            if(g == 0) {
                if(j > 1 && j < 5 && d != 1) koncowkaUlamka.append("e ");
                else koncowkaUlamka.append("ych ");
            }

            if (d == 1 && j > 0) {// if zajmujący się nastkami
                n = j;
                d = 0;
                j = 0;
            } else {
                n = 0;
            }
            // KOŃCÓWKI
            if (j == 1 & s + d + n == 0) {
                k = 0;
                if (s + d == 0 && g > 0) { // jeśli nie będzie dziesiątek ani setek, wtedy otrzymamy samą grupę
                    j = 0;
                    word = new StringBuilder(grupy[(int) g][(int) k] + word.toString());
                }
            } else if (j == 2 || j == 3 || j == 4) k = 1;
            else k = 2;

            // KONIEC KOŃCÓWEK -->
            if (s+d+n+j > 0) {
                if(fraction && g == 0 && d != 1 && j == 2)
                    word = new StringBuilder(setki[(int) s] + dziesiatki[(int) d] + nastki[(int) n]
                        + ulamki[(int) j] + grupy[(int) g][(int) k] + word.toString());
                else word = new StringBuilder(setki[(int) s] + dziesiatki[(int) d] + nastki[(int) n]
                        + jednosci[(int) j] + grupy[(int) g][(int) k] + word.toString());
            }
            number = number / 1000;
            g = g + 1;
        }
        if(newSentence) word.setCharAt(0, Character.toUpperCase(word.toString().charAt(0)));
        if(fraction) word.append(koncowkaUlamka.toString());
        return word.toString().substring(0, word.toString().length()-1); //Zwraca stringa bez ostatniego znaku - spacji
    }
}
