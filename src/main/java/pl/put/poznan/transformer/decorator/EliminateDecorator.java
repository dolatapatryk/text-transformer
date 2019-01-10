package pl.put.poznan.transformer.decorator;

import pl.put.poznan.transformer.logic.Transformer;
import pl.put.poznan.transformer.utils.Transformation;

public class EliminateDecorator extends TransformerDecorator {
    
    public EliminateDecorator(Transformer transformer) {
        super(transformer);
    }
    
    @Override
    public String transform(String text) {
        return eliminate(transformer.transform(text));
    }

    /**
     * Metoda eliminująca powtarzające się wyrazy w bezpośrednim sąsiedztwie
     * @param text Wyrażenie do edycji
     * @return Wyrażenie bez powtarzających się słów
     */
    public String eliminate(String text) {
        StringBuilder result=new StringBuilder("");
        String[] tab = text.split("[ ]+");
        if(tab.length > 0) {
            result.append(tab[0]);
            for(int i = 1; i < tab.length; i++) {
                if(!tab[i].endsWith(".") &&
                        !tab[i].endsWith(",") &&
                        !tab[i].endsWith("!") &&
                        !tab[i].endsWith("?") &&
                        !tab[i].endsWith(":") &&
                        !tab[i].endsWith(";") &&
                        !tab[i].endsWith("-") &&
                        !tab[i].endsWith("+")) {
                    if(!tab[i].toLowerCase().equals(tab[i-1].toLowerCase())) {
                        result.append(" ");
                        result.append(tab[i]);
                    }
                } else {
                    if(tab[i].toLowerCase().matches(tab[i-1].toLowerCase()+tab[i].charAt(tab[i].length()-1)))
                        result.append(tab[i].charAt(tab[i].length()-1));
                    else {
                        result.append(" ");
                        result.append(tab[i]);
                    }

                }
            }
        }
        return result.toString();
    }
}
