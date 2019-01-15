/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.put.poznan.transformer.utils;

import org.junit.Test;
import static org.junit.Assert.*;
import pl.put.poznan.transformer.logic.OptionModel;

/**
 *
 * @author patryk
 */
public class ResponseMessageTest {   

    /**
     * Test metody objectToJson, z klasy ResponseMessage.
     */
    @Test
    public void testObjectToJson() {
        OptionModel option = new OptionModel();
        option.setName("opcja");
        option.setDescription("opis");
        
        String expResult = "{\"name\":\"opcja\",\"description\":\"opis\"}";
        String result = ResponseMessage.objectToJson(option);
        assertEquals(expResult, result);
    }
    
}
