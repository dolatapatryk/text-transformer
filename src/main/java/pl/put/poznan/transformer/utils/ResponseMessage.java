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
     * status używany przy tworzeniu własnej transformacji, informuje o tym, że nazwa jest już zajęta
     */
    public static String nameTaken = "{\"status\":\"Ta nazwa jest już zajęta\"}";
    
    /**
     * Metoda zmieniająca obiekt na obiekt Json
     * @param object obiekt do zamiany
     * @return obiekt w formacie Json
     */
    public static String objectToJson(Object object) {
        return new GsonBuilder().serializeNulls().create().toJson(object);
    }
}
