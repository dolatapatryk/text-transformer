package pl.put.poznan.transformer.rest;

import java.util.Arrays;
import java.util.Optional;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import static pl.put.poznan.transformer.app.TextTransformerApplication.userTransforms;
import pl.put.poznan.transformer.logic.UserTransformModel;
import pl.put.poznan.transformer.utils.ResponseMessage;
import pl.put.poznan.transformer.utils.Utils;

/**
 *
 * @author patrykdolata
 */
@RestController
@RequestMapping("/create_transformation/")
public class CreateTransformationController {
    
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CreateTransformationController.class);

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@RequestBody String input) {
        UserTransformModel userTransform = Utils.objectFromJson(input, UserTransformModel.class);
        
        logger.debug(userTransform.getName());
        logger.debug(Arrays.toString(userTransform.getTransforms()));
        
        if(Utils.checkIfNameIsTaken(userTransform.getName())) {
            logger.info("Nazwa jest już zajęta");
            return ResponseMessage.nameTaken;
        }
        
        userTransforms.add(userTransform);
        
        logger.info("Utworzono własny ciąg transformacji o nazwie: {} i transformacjach: {}", userTransform.getName(),
                userTransform.getTransforms());
        
        return ResponseMessage.ok;
    }
    
    @RequestMapping(value = "{name}",method = RequestMethod.PUT, produces = "application/json")
    public String put(@PathVariable String name, @RequestBody String input) {
        String[] newTransforms = Utils.objectFromJson(input, String[].class);
        
        UserTransformModel userTransform = new UserTransformModel();
        Optional<UserTransformModel> userTransformOpt = userTransforms.stream().
                filter(t -> t.getName().equals(name)).findAny();
        
        if(userTransformOpt.isPresent())
            userTransform = userTransformOpt.get();
        else 
            return ResponseMessage.transformNotFound;
        
        userTransform.setTransforms(newTransforms);
        logger.info("Zaktualizowano ciąg transformacji o nazwie: {}, jej nowe transformacje to: {} ", userTransform.getName(),
                userTransform.getTransforms());
        
        return ResponseMessage.ok;
    }
}
