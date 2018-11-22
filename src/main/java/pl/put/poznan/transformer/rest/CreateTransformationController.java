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
import static pl.put.poznan.transformer.app.TextTransformerApplication.userTransforms;
import pl.put.poznan.transformer.logic.UserTransformModel;
import pl.put.poznan.transformer.utils.ResponseMessage;

/**
 *
 * @author patrykdolata
 */
@RestController
@RequestMapping("/create_transformation")
public class CreateTransformationController {
    
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CreateTransformationController.class);

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@RequestBody String input) {
        ObjectMapper mapper = new ObjectMapper();
        UserTransformModel userTransform = null;
        try {
            userTransform = mapper.readValue(input, UserTransformModel.class);
        } catch (IOException ex) {
            Logger.getLogger(CreateTransformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        logger.debug(userTransform.getName());
        logger.debug(Arrays.toString(userTransform.getTransforms()));
        
        userTransforms.add(userTransform);
        
        logger.info("Utworzono własną transformacje o nazwie: {} i transformacjach: {}", userTransform.getName(),
                userTransform.getTransforms());
        
        return ResponseMessage.ok;
    }
}
