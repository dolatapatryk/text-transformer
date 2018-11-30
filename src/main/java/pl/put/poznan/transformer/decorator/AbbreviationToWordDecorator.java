package pl.put.poznan.transformer.decorator;

import pl.put.poznan.transformer.logic.Transformer;
import pl.put.poznan.transformer.utils.Transformation;

public class AbbreviationToWordDecorator extends TransformerDecorator {
    
    public AbbreviationToWordDecorator(Transformer transformer) {
        super(transformer);
    }
    
    @Override
    public String transform(String text) {
        return Transformation.abbreviationToWord(text);
    }
}
