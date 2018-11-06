package pl.put.poznan.transformer.utils;

public class Transformation {
    
    /**
     * Metoda do odwracania tekstu z zachowaniem wielkości liter na odpowiednich pozycjach
     * @param text
     * @return 
     */
    public static String inverse(String text) {
        String reverse = reverse(text);
        
        String inverse = keepLettersSize(text, reverse);
        
        return inverse;
    }
    
    /**
     * Metoda do odwracania tekstu
     * @param text - oryginalny tekst
     * @return 
     */
    private static String reverse(String text) {
        return new StringBuilder(text).reverse().toString();
    }
    
    /**
     * Metoda do zachowania wielkości liter na konkretnych pozycjach
     * @param src - tekst z oryginalna wielkością liter
     * @param dest - przetworzony wczesniej tekst, w którym chcemy zachowac wielkosc liter ze źródła
     * @return 
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
     * Metoda rozpoznająca liczby i zapisująca je za pomocą słów
     * @param src - oryginalny tekst
     * @return 
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
                }
                number.append(src.charAt(i));
            } else {
                if(i + 1 < src.length() && src.charAt(i) == ',' && Character.isDigit(src.charAt(i+1)) && !fraction) {
                    dest.append(convertNum(Integer.parseInt(number.toString()), newSent, fraction, number.length()));
                    dest.append(" i ");
                    fraction = true;
                    num = false;
                } else {
                    if(num) {
                        num = false;
                        dest.append(convertNum(Integer.parseInt(number.toString()), newSent, fraction, number.length()));
                        if(fraction) fraction = false;
                    }
                    dest.append(src.charAt(i));
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
     * @param number - liczba poddawana konwersji na tekst
     * @param newSentence - zmienna logiczna przechowująca informację o tym, czy liczba znajduje się na początku zdania
     * @param fraction - zmienna logiczna przechowująca informację o tym, czy liczba jest częścią ułamkową
     * @param length - długość liczby w zapisie dziesiętnym - kluczowe przy części ułamkowej
     * @return 
     */
    private static String convertNum(Integer number, Boolean newSentence, Boolean fraction, Integer length) {
        String zaduzy = "!za duża ilość cyfr po przecinku! ";
        if(length > 20 && fraction) return zaduzy;
        
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
        return word.toString().substring(0, word.toString().length()-1);
    }
}
