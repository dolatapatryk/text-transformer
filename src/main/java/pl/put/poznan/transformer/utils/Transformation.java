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
}
