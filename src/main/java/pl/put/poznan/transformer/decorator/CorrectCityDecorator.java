package pl.put.poznan.transformer.decorator;

import pl.put.poznan.transformer.logic.Transformer;
import pl.put.poznan.transformer.utils.Transformation;

/**
 *
 * @author patrykdolata
 */
public class CorrectCityDecorator extends TransformerDecorator {

    public CorrectCityDecorator(Transformer transformer) {
        super(transformer);
    }
    
    @Override
    public String transform(String text) {
        return Transformation.correctCity(transformer.transform(text));
    }

}
