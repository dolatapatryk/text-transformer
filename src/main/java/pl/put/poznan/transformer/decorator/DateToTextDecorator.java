/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.put.poznan.transformer.decorator;

import pl.put.poznan.transformer.logic.Transformer;
import pl.put.poznan.transformer.utils.Transformation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static pl.put.poznan.transformer.utils.Transformation.parseYear;
import static pl.put.poznan.transformer.utils.Transformation.strToInt;

/**
 *
 * @author Domin
 */
public class DateToTextDecorator extends TransformerDecorator {
    
    public DateToTextDecorator(Transformer transformer) {
        super(transformer);
    }
    
    @Override
    public String transform(String text) {
        return dateToText(transformer.transform(text));
    }

    /**
     * Metoda rozpoznająca datę i zapisująca miesiące słownie
     * @param src oryginalny tekst
     * @return tekst z datami zapisanymi jako słowa
     */
    public String dateToText(String src) {
        String result = src;

        Pattern pattern = Pattern.compile("([0-2][0-9]|(3)[0-1])(\\.)(((0)[0-9])|((1)[0-2]))(\\.)\\d{2,4}");
        Matcher matcher = pattern.matcher(src);

        String[] miesiace = {
                "stycznia",
                "lutego",
                "marca",
                "kwietnia",
                "maja",
                "czerwca",
                "lipa",
                "sierpnia",
                "września",
                "października",
                "listopada",
                "grudnia"
        };



        String date = "";
        while (matcher.find()) {
            String[] parts = matcher.group().split("\\.");
            if(parts.length == 3){
                date = parts[0] + " " + miesiace[strToInt(parts[1]) - 1] + " " + parseYear(parts[2]);

                result = result.replace(matcher.group(),date);
            }

        }

        return result;
    }
}
