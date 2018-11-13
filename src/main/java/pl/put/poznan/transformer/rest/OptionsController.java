package pl.put.poznan.transformer.rest;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.transformer.logic.OptionModel;
import pl.put.poznan.transformer.utils.ResponseMessage;

@RestController
@RequestMapping("/options/")
public class OptionsController {
    
    private static final Logger logger = LoggerFactory.getLogger(OptionsController.class);
    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get() {
        Gson gson = new Gson();
        JsonReader jsonReader = null;
        
        try {
            jsonReader = new JsonReader(new InputStreamReader(new FileInputStream("options.json"), "UTF-8"));
            logger.debug("Wczytano opcje z jsona");
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(OptionsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            java.util.logging.Logger.getLogger(OptionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        OptionModel[] options = gson.fromJson(jsonReader, OptionModel[].class);
        logger.info("Dostępne opcje: ");
        Arrays.asList(options).forEach(o -> logger.info(o.getName()));
        
        return ResponseMessage.objectToJson(options);
    }
}
