package pl.put.poznan.transformer.decorator;

import pl.put.poznan.transformer.logic.Transformer;
import pl.put.poznan.transformer.utils.Transformation;

public class CapitalizeDecorator extends TransformerDecorator {
    
    public CapitalizeDecorator(Transformer transformer) {
        super(transformer);
    }
    
    @Override
    public String transform(String text) {
        return Transformation.capitalize(transformer.transform(text));
    }
}
