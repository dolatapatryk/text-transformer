package pl.put.poznan.transformer.decorator;

import pl.put.poznan.transformer.logic.Transformer;
import pl.put.poznan.transformer.utils.Transformation;

public class UpperDecorator extends TransformerDecorator {
    
    public UpperDecorator(Transformer transformer) {
        super(transformer);
    }
    
   @Override
   public String transform(String text) {
       return Transformation.upper(transformer.transform(text));
   }
}
