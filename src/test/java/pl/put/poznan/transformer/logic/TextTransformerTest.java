/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.put.poznan.transformer.logic;

import org.junit.Test;
import static org.junit.Assert.*;
import static pl.put.poznan.transformer.app.TextTransformerApplication.userTransforms;

/**
 *
 * @author patryk
 */
public class TextTransformerTest {
    
    @Test
    public void testDecorate() {
        String[] transforms ={ "inverse"};
        TextTransformer instance = new TextTransformer(transforms);
        Transformer result = instance.decorate(transforms[0]);
        assertEquals("kerim", result.transform("mirek"));
    }
    /**
     * Test of transform method, of class TextTransformer.
     */
    @Test
    public void testTransform() {
        String text = "polibuda";
        TextTransformer instance = new TextTransformer(new String[]{"upper"});
        String expResult = "POLIBUDA";
        String result = instance.transform(text);
        assertEquals(expResult, result);
    }

    /**
     * Test of transformWithUserTransforms method, of class TextTransformer.
     */
    @Test
    public void testTransformWithUserTransforms() {
        String text = "Wpłać -12,67 złotego";
        UserTransformModel userTransform = new UserTransformModel("moja");
        userTransform.setTransforms(new String[]{"inverse","upper"});
        userTransforms.add(userTransform);
        
        TextTransformer instance = new TextTransformer(new String[]{"moja"});
        String expResult = "OGETOŁZ 76,21- ĆAŁPW";
        Transformer result = instance.decorateWithUserTransforms(userTransform);
        assertEquals(expResult, result.transform(text));
    }

    
}
