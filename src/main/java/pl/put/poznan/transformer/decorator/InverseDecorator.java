package pl.put.poznan.transformer.decorator;

import pl.put.poznan.transformer.logic.Transformer;
import pl.put.poznan.transformer.utils.Transformation;

public class InverseDecorator extends TransformerDecorator {
    
    public InverseDecorator(Transformer transformer) {
        super(transformer);
    }
    
    @Override
    public String transform(String text) {
        return Transformation.inverse(transformer.transform(text));
    }
    
}
