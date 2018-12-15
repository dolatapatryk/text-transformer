package pl.put.poznan.transformer.decorator;

import pl.put.poznan.transformer.logic.Transformer;
import pl.put.poznan.transformer.utils.Transformation;

public class WordToAbbreviationDecorator extends TransformerDecorator {
    
    public WordToAbbreviationDecorator(Transformer transformer) {
        super(transformer);
    }
    
    @Override
    public String transform(String text) {
        return Transformation.wordToAbbreviation(transformer.transform(text));
    }
}
