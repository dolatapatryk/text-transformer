package pl.put.poznan.transformer.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        TextTransformer transformer = new TextTransformer(text.getTransforms());
        TextModel result = new TextModel();
        
        result.setText(transformer.transform(text.getText()));
        
        return ResponseMessage.objectToJson(result);
    }
}
