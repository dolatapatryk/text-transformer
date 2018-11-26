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
    
    public TextTransformerTest() {
    }
    
    /**
     * Test metody transform, z klasy TextTransformer.
     */
    @Test
    public void testTransform() {
        String text = "MirEk";
        String transform = "inverse";
        TextTransformer instance = new TextTransformer(new String[]{transform});
        String expResult = "KerIm";
        String result = instance.transform(text, transform);
        assertEquals(expResult, result);
    }

    /**
     * Test głównej metody transformującej, z klasy TextTransformer.
     */
    @Test
    public void testSuperTransform() {
        String text = "Wpłać -12,67 złotego";
        UserTransformModel userTransform = new UserTransformModel("moja");
        userTransform.setTransforms(new String[]{"inverse","upper"});
        userTransforms.add(userTransform);
        
        String[] transforms = {"numberToText", "moja"};
        TextTransformer instance = new TextTransformer(transforms);
        String expResult = "OGETOŁZ HCYNTES MEDEIS TĄISEIZDĆŚEZS I EICŚANAWD SUNIM ĆAŁPW";
        String result = instance.superTransform(text);
        assertEquals(expResult, result);
    }

    /**
     * Test metody transformWithUserTransforms, z klasy TextTransformer.
     */
    @Test
    public void testTransformWithUserTransforms() {
        String text = "Wpłać -12,67 złotego";
        UserTransformModel userTransform = new UserTransformModel("moja");
        userTransform.setTransforms(new String[]{"inverse","upper"});
        userTransforms.add(userTransform);
        
        TextTransformer instance = new TextTransformer(new String[]{"moja"});
        String expResult = "OGETOŁZ 76,21- ĆAŁPW";
        String result = instance.transformWithUserTransforms(text, userTransform);
        assertEquals(expResult, result);
    }

    
}
