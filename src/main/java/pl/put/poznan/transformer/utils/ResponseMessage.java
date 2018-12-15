package pl.put.poznan.transformer.utils;

import com.google.gson.GsonBuilder;
/**
 * Klasa pomocnicza posiadająca odpowiedzi na żądania użytkownika
 * @author patryk, marcel, artur, dominik
 */
public class ResponseMessage {
    
    /**
     * status mówiący o tym, że wszystko się powiodło
     */
    public static String ok = "{\"status\":\"OK\"}";
    
    /**
     * status używany przy tworzeniu własnego ciągu transfomacji, informuje o tym, że nazwa jest już zajęta
     */
    public static String nameTaken = "{\"status\":\"Ta nazwa jest już zajęta\"}";
    
    /**
     * status, który informuje, że nie ma zdefiniowanej transformacji o takiej nazwie
     */
    public static String transformNotFound = "{\"status\":\"Nie ma zdefiniowanej transformacji o takiej nazwie\"}";
    
    /**
     * Metoda zmieniająca obiekt na obiekt Json
     * @param object obiekt do zamiany
     * @return obiekt w formacie Json
     */
    public static String objectToJson(Object object) {
        return new GsonBuilder().serializeNulls().create().toJson(object);
    }
}
