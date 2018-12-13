/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.put.poznan.transformer.decorator;

import pl.put.poznan.transformer.logic.Transformer;
import pl.put.poznan.transformer.utils.Transformation;

/**
 *
 * @author Domin
 */
public class DateToTextDecorator extends TransformerDecorator {
    
    public DateToTextDecorator(Transformer transformer) {
        super(transformer);
    }
    
    @Override
    public String transform(String text) {
        return Transformation.dateToText(transformer.transform(text));
    }
    
}
