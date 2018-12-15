package pl.put.poznan.transformer.decorator;

import pl.put.poznan.transformer.logic.Transformer;
import pl.put.poznan.transformer.utils.Transformation;

public class CapitalizeSentencesDecorator extends TransformerDecorator {
    
    public CapitalizeSentencesDecorator(Transformer transformer) {
        super(transformer);
    }
    
    @Override
    public String transform(String text) {
        return Transformation.capitalizeSentences(transformer.transform(text));
    }
}
