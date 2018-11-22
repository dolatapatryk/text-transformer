package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import pl.put.poznan.transformer.logic.TextModel;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.utils.ResponseMessage;


@RestController
@RequestMapping("/{text}")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String text,
                              @RequestParam(value="transforms", defaultValue="upper") String[] transforms) {

        logger.debug(text);
        logger.debug(Arrays.toString(transforms));

        TextTransformer transformer = new TextTransformer(transforms);
        TextModel result = new TextModel();
        
        result.setText(transformer.superTransform(text));
        
        return ResponseMessage.objectToJson(result);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@PathVariable String text,
                      @RequestBody String[] transforms) {

        logger.debug(text);
        logger.debug(Arrays.toString(transforms));
        

        TextTransformer transformer = new TextTransformer(transforms);
        return transformer.superTransform(text);
    }



}


