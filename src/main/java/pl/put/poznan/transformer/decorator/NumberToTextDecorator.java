package pl.put.poznan.transformer.decorator;

import pl.put.poznan.transformer.logic.Transformer;
import pl.put.poznan.transformer.utils.Transformation;

public class NumberToTextDecorator  extends TransformerDecorator {
    
    public NumberToTextDecorator(Transformer transformer) {
        super(transformer);
    }
    
    @Override
    public String transform(String text) {
        return Transformation.numberToText(transformer.transform(text));
    }
    
}
