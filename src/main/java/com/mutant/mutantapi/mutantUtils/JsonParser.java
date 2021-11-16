package com.mutant.mutantapi.mutantUtils;

import com.google.gson.Gson;

public class JsonParser{
    public static String toJsonString(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}
