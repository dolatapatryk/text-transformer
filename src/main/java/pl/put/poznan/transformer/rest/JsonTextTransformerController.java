package pl.put.poznan.transformer.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.transformer.logic.InputModel;
import pl.put.poznan.transformer.logic.TextModel;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.utils.ResponseMessage;

@RestController
@RequestMapping("/json/")
public class JsonTextTransformerController {
    
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TextTransformerController.class);
    
    /**
     * Postowanie naszego tekstu i transformacji w postaci jsona, przykładowy json:
     * {
     *      "text": "MirEk",
     *      "transforms": [
     *          "inverse",
     *          "upper"
     *      ]
     * }
     * @param input
     * @return 
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@RequestBody String input) {
        ObjectMapper mapper = new ObjectMapper();
        InputModel text = null;
        try {
            text = mapper.readValue(input, InputModel.class);
        } catch (IOException ex) {
            Logger.getLogger(JsonTextTransformerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        logger.debug(text.getText());
        logger.debug(Arrays.toString(text.getTransforms()));
        
        TextTransformer transformer = new TextTransformer(text.getTransforms());
        TextModel result = new TextModel();
        
        result.setText(transformer.transform(text.getText()));
        
        return ResponseMessage.objectToJson(result);
    }
}
