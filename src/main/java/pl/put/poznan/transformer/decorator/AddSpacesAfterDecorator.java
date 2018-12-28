package pl.put.poznan.transformer.decorator;

import pl.put.poznan.transformer.logic.Transformer;
import pl.put.poznan.transformer.utils.Transformation;

public class AddSpacesAfterDecorator extends TransformerDecorator {
    
    private char symbol;
    
    public AddSpacesAfterDecorator(Transformer transformer, char symbol) {
        super(transformer); 
        this.symbol = symbol; 
    }

    @Override
    public String transform(String text) { 
        return Transformation.addSpacesAfter(transformer.transform(text),symbol); 
    }
}
