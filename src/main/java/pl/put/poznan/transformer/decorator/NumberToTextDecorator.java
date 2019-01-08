package pl.put.poznan.transformer.decorator;

import pl.put.poznan.transformer.logic.Transformer;
import static pl.put.poznan.transformer.utils.Transformation.convertNum;

public class NumberToTextDecorator extends TransformerDecorator {
    
    public NumberToTextDecorator(Transformer transformer) {
        super(transformer);
    }
    
    @Override
    public String transform(String text) {
        return numberToText(transformer.transform(text));
    }
    
    /**
     * Metoda rozpoznająca liczby i zapisująca je za pomocą słów
     * @param src oryginalny tekst
     * @return tekst z liczbami zapisanymi jako słowa
     */
    public static String numberToText(String src) {
        StringBuilder dest=new StringBuilder("");
        StringBuilder number=new StringBuilder("");
        boolean num = false, newSent = true, fraction = false;
        for(int i = 0; i < src.length(); i++) {
            if(Character.isDigit(src.charAt(i))) {
                if(!num){
                    number=new StringBuilder("");
                    num = true;
                    if(i - 1 > -1) if(!Character.isWhitespace(i-1)) dest.append(" ");
                }
                number.append(src.charAt(i));
            } else {
                if(number.length() > 0 && i + 1 < src.length() && src.charAt(i) == ',' && Character.isDigit(src.charAt(i+1)) && !fraction) {
                    if(Integer.parseInt(number.toString()) != 0) {
                        dest.append(convertNum(Integer.parseInt(number.toString()), newSent, fraction, number.length()));
                        dest.append(" i ");
                    }
                    fraction = true;
                    num = false;
                } else {
                    if(num) {
                        num = false;
                        dest.append(convertNum(Integer.parseInt(number.toString()), newSent, fraction, number.length()));
                        if(fraction) fraction = false;
                    }
                    if(i + 1 < src.length() && src.charAt(i) == '-' && Character.isDigit(src.charAt(i+1)) && !fraction){
                        if(i - 1 > -1 && !newSent) {
                            if(Character.isWhitespace(src.charAt(i-1))) dest.append("minus");
                            else dest.append(" minus");
                        }
                        else if(i - 1 > -1 && newSent) {
                            if(Character.isWhitespace(src.charAt(i-1))) {
                                dest.append("Minus");
                                newSent = false;
                            } else if(src.charAt(i-1) == '.') {
                                dest.append(" Minus");
                                newSent = false;
                            }
                        }
                        if(i == 0) dest.append("Minus");
                    } else {
                        if(i - 1 > -1 && !Character.isWhitespace(src.charAt(i)) && src.charAt(i) != '.' && src.charAt(i) != ',')
                            if(Character.isDigit(src.charAt(i-1))) dest.append(" ");
                        dest.append(src.charAt(i));
                    }
                    if(!Character.isWhitespace(src.charAt(i))) newSent = false;
                    if(src.charAt(i) == '.') newSent = true;
                }
            }
        }
        if(num) dest.append(convertNum(Integer.parseInt(number.toString()), newSent, fraction, number.length()));
        return dest.toString();
    }
}
