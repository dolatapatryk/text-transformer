package pl.put.poznan.transformer.decorator;

import pl.put.poznan.transformer.logic.Transformer;
import pl.put.poznan.transformer.utils.Transformation;

public class AddDotDecorator extends TransformerDecorator {
    
    public AddDotDecorator(Transformer transformer) {
        super(transformer);
    }
    
    @Override
    public String transform(String text) {
        return addDot(transformer.transform(text));
    }

    /**
     * Metoda dodająca kropkę na końcu zdania
     * @param text zdanie, które ma zostać zakończone kropką
     * @return wyrażenie z kropką na końcu
     */
    public String addDot(String text) {
        StringBuilder result = new StringBuilder(text);
        if(!text.endsWith("."))
            result.append(".");
        text = result.toString();

        return text;
    }
}
