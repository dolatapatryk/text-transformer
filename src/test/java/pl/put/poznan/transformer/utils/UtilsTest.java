/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.put.poznan.transformer.utils;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;
import static pl.put.poznan.transformer.app.TextTransformerApplication.userTransforms;
import pl.put.poznan.transformer.logic.OptionModel;
import pl.put.poznan.transformer.logic.UserTransformModel;

/**
 *
 * @author patryk
 */
public class UtilsTest {
    
    public UtilsTest() {
    }
    

    /**
     * Test metody checkIfNameIsTaken, z klasy Utils.
     */
    @Test
    public void testCheckIfNameIsTaken() {
        String name = "inverse";
        boolean expResult = true;
        boolean result = Utils.checkIfNameIsTaken(name);
        assertEquals(expResult, result);
    }

    /**
     * Test generycznej metody objectFromJson, z klasy Utils.
     */
    @Test
    public void testObjectFromJson() {
        String json = "{\"name\":\"opcja\", \"description\":\"opis\"}";
        OptionModel expResult = new OptionModel();
        expResult.setName("opcja");
        expResult.setDescription("opis");
        OptionModel result = Utils.objectFromJson(json, OptionModel.class);
        assertEquals(expResult.getName(), result.getName());
        assertEquals(expResult.getDescription(), result.getDescription());
    }

    /**
     * Test metody getTransformWithGivenName, z klasy Utils.
     */
    @Test
    public void testGetTransformWithGivenName() {
        UserTransformModel newTransform = new UserTransformModel();
        newTransform.setName("moja");
        newTransform.setTransforms(new String[]{"inverse", "upper"});
        userTransforms.add(newTransform);
        String name = "moja";
        UserTransformModel expResult = newTransform;
        UserTransformModel result = Utils.getTransformWithGivenName(name);
        assertEquals(expResult, result);
    }

    /**
     * Test metody checkUserTransforms, z klasy Utils.
     */
    @Test
    public void testCheckUserTransforms() {
        UserTransformModel new1 = new UserTransformModel("nowa1");
        UserTransformModel new2 = new UserTransformModel("nowa2");
        userTransforms.addAll(Arrays.asList(new1, new2));
        String name = "nowa3";
        boolean expResult = false;
        boolean result = Utils.checkUserTransforms(name);
        assertEquals(expResult, result);
    }
    
}
