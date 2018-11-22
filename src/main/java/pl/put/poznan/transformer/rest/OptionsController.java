package pl.put.poznan.transformer.rest;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.transformer.logic.OptionModel;
import pl.put.poznan.transformer.utils.ResponseMessage;
import pl.put.poznan.transformer.utils.Utils;

@RestController
@RequestMapping("/options/")
public class OptionsController {
    
    private static final Logger logger = LoggerFactory.getLogger(OptionsController.class);
    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get() {
        OptionModel[] options = Utils.loadOptions();
        
        logger.info("Dostępne opcje: ");
        Arrays.asList(options).forEach(o -> logger.info(o.getName()));
        
        return ResponseMessage.objectToJson(options);
    }
}
