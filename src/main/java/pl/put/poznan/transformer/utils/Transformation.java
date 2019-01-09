package pl.put.poznan.transformer.utils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Klasa poasiadająca metody transformacji na tekście
 * @author patryk, marcel, artur, dominik
 */
public class Transformation {
    
    private static final Logger logger = LoggerFactory.getLogger(Transformation.class);
    
    /**
     * Metoda do odwracania tekstu
     * @param text oryginalny tekst
     * @return odwrócony tekst
     */
    public static String reverse(String text) {
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
     * Metoda konwertująca liczbę na tekst
     * @param number liczba poddawana konwersji na tekst
     * @param newSentence zmienna logiczna przechowująca informację o tym, czy liczba znajduje się na początku zdania
     * @param fraction zmienna logiczna przechowująca informację o tym, czy liczba jest częścią ułamkową
     * @param length długość liczby w zapisie dziesiętnym - kluczowe przy części ułamkowej
     * @return tekst reprezentujący podaną liczbę
     */
    public static String convertNum(Integer number, Boolean newSentence, Boolean fraction, Integer length) {
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
    
    /**
     * Metoda dodająca kropkę na końcu zdania
     * @param text zdanie, które ma zostać zakończone kropką
     * @return wyrażenie z kropką na końcu
     */
    public static String addDot(String text) {
        StringBuilder result = new StringBuilder(text);
        if(!text.endsWith("."))
            result.append(".");
        text = result.toString();
        
        return text;
    }
    
    /**
     * Metoda eliminująca powtarzające się wyrazy w bezpośrednim sąsiedztwie
     * @param text Wyrażenie do edycji
     * @return Wyrażenie bez powtarzających się słów
     */
    public static String eliminate(String text) {
        StringBuilder result=new StringBuilder("");
        String[] tab = text.split("[ ]+");
        if(tab.length > 0) {
            result.append(tab[0]);
            for(int i = 1; i < tab.length; i++) {
                if(!tab[i].endsWith(".") &&
                   !tab[i].endsWith(",") &&
                   !tab[i].endsWith("!") &&
                   !tab[i].endsWith("?") &&
                   !tab[i].endsWith(":") &&
                   !tab[i].endsWith(";") &&
                   !tab[i].endsWith("-") &&
                   !tab[i].endsWith("+")) {
                    if(!tab[i].toLowerCase().equals(tab[i-1].toLowerCase())) {
                        result.append(" ");
                        result.append(tab[i]);
                    }
                } else {
                    if(tab[i].toLowerCase().matches(tab[i-1].toLowerCase()+tab[i].charAt(tab[i].length()-1)))
                        result.append(tab[i].charAt(tab[i].length()-1));
                    else {
                        result.append(" ");
                        result.append(tab[i]);
                    }
                        
                }
            }
        }
        return result.toString();
    }
    
     /**
     * Metoda rozpoznająca datę i zapisująca miesiące słownie
     * @param src oryginalny tekst
     * @return tekst z datami zapisanymi jako słowa
     */
    public static String dateToText(String src) {
        String result = src;
        
        Pattern pattern = Pattern.compile("([0-2][0-9]|(3)[0-1])(\\.)(((0)[0-9])|((1)[0-2]))(\\.)\\d{2,4}");
        Matcher matcher = pattern.matcher(src);
        
        String[] miesiace = {
            "stycznia",
            "lutego",
            "marca",
            "kwietnia",
            "maja",
            "czerwca",
            "lipa",
            "sierpnia",
            "września",
            "października",
            "listopada",
            "grudnia"
        };
        
        
        
        String date = "";
        while (matcher.find()) {
            String[] parts = matcher.group().split("\\.");
            if(parts.length == 3){
                date = parts[0] + " " + miesiace[strToInt(parts[1]) - 1] + " " + parseYear(parts[2]);
               
                result = result.replace(matcher.group(),date);
            }
            
        }
        
        return result;
    }
    
    /**
     * Zamienia string na int w specyficzny sposób, potrzebne do parsowania roku
     * @param str wejściowy napis
     * @return napis zamieniony na integer
     */
    public static int strToInt(String str){
        int number = 0;
        
        switch(str.charAt(0)){
            case '0': 
                number = str.charAt(1);
                break;           
            case '1': 
                number = Integer.parseInt(str);
                break;
                
        }
        return number;
    }
    
    /**
     * Parsuje rok na czterocyfrowy rok np 18 - 2018
     * @param str wejściowy napis z rokiem
     * @return zparsowany rok
     */
    public static String parseYear(String str){
        String year = "";
        
        switch(str.length()){
            case 2: 
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                
                if(currentYear < Integer.parseInt("20" + str)){
                    year = "19" + str;
                } else {
                    year = "20" + str;
                }
                break;  
            case 3: 
                year = "2" + str;
                break;                 
            case 4: 
                year = str;
                break;
                
        }
        return year;
    }
}
