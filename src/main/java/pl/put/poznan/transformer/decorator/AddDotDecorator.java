package pl.put.poznan.transformer.decorator;

import pl.put.poznan.transformer.logic.Transformer;
import pl.put.poznan.transformer.utils.Transformation;

public class AddDotDecorator extends TransformerDecorator {
    
    public AddDotDecorator(Transformer transformer) {
        super(transformer);
    }
    
    @Override
    public String transform(String text) {
        return Transformation.addDot(transformer.transform(text));
    }
}
