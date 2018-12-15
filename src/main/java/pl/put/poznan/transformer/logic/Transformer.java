package pl.put.poznan.transformer.logic;

/**
 * Interfejs, który będzie implementowy przez dekorowany obiekt oraz dekorator
 * @author patryk
 */
public interface Transformer {
    
    /**
     * Metoda transformująca tekst
     * @param text wejściowy tekst
     * @return ztransformowany tekst
     */
   public  String transform(String text);
}
