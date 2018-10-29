package pl.put.poznan.transformer.logic;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class TextTransformer {

    private final String[] transforms;

    public TextTransformer(String[] transforms){
        this.transforms = transforms;
    }

    public String transform(String text){
        for(String transform : transforms) {
            if(transform.equals("inverse"))
                text = inverse(text);
            //tu inne opcje
        }
        
        return text;
    }
    
    public String inverse(String text) {
        String reverse = reverse(text);
        String inverse = reverse.toLowerCase();
        
        for(int i = 0; i < text.length(); i++) {
            if(Character.isUpperCase(text.charAt(i))) {
                StringBuilder temp = new StringBuilder(inverse);
                temp.setCharAt(i, Character.toUpperCase(inverse.charAt(i)));
                inverse = temp.toString();
            }              
        }
        
        return inverse;
    }
    
    private String reverse(String text) {
        return new StringBuilder(text).reverse().toString();
    }
    
}
