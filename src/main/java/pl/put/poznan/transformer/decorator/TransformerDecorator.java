package pl.put.poznan.transformer.decorator;

import pl.put.poznan.transformer.logic.Transformer;

public abstract class TransformerDecorator implements Transformer {
    
    protected Transformer transformer;
    
    public TransformerDecorator(Transformer transformer) {
        this.transformer = transformer;
    }
    
    public String transform(String text) {
        return transformer.transform(text);
    }
}
