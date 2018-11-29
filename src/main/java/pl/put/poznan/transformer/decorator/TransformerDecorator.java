package pl.put.poznan.transformer.decorator;

import pl.put.poznan.transformer.logic.Transformer;

/**
 * Abstrakcyjna klasa dekoratora
 * @author patryk
 */
public abstract class TransformerDecorator implements Transformer {
    
    /**
     * Obiekt klasy Transformer, który będzie dekorowany
     */
    protected Transformer transformer;
    
    /**
     * Konstruktor dekoratora przyjmujący obiekt do dekorowania
     * @param transformer 
     */
    public TransformerDecorator(Transformer transformer) {
        this.transformer = transformer;
    }
    
    /**
     * Metoda transformująca tekst
     * @param text wejściowy tekst
     * @return ztransformowany tekst
     */
    public String transform(String text) {
        return transformer.transform(text);
    }
}
