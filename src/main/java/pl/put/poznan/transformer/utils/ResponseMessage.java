package pl.put.poznan.transformer.utils;

import com.google.gson.GsonBuilder;

public class ResponseMessage {
    
    //Possible response messages
    public static String ok = "{\"status\":\"OK\"}";
    
    public static String objectToJson(Object object) {
        return new GsonBuilder().serializeNulls().create().toJson(object);
    }
}
