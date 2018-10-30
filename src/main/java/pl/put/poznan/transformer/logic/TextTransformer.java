package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.utils.Transformation;

public class TextTransformer {

    private final String[] transforms;

    public TextTransformer(String[] transforms){
        this.transforms = transforms;
    }
    /**
     * Głowna metoda transformująca, bierze każdą transformacje po kolei, więc kolejność
     * transformacji w requescie ma znaczenie!!
     * @param text
     * @return 
     */
    public String transform(String text){
        for(String transform : transforms) {
            if(transform.equals("inverse"))
                text = Transformation.inverse(text);
            if(transform.equals("upper"))
                text = text.toUpperCase();
            if(transform.equals("lower"))
                text = text.toLowerCase();
            //tu inne opcje
        }
        
        return text;
    }
    
}
