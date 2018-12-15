package pl.put.poznan.transformer.app;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.transformer.logic.UserTransformModel;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class TextTransformerApplication {
    
    public static List<UserTransformModel> userTransforms = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(TextTransformerApplication.class, args);
    }
}
