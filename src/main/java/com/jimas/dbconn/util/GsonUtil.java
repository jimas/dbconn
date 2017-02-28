package com.jimas.dbconn.util;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;

public class GsonUtil {
    public String toJsonString(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public <T> Object parseJson(String json, Class<T> c) {
        Gson gson = new Gson();
        return gson.fromJson(json, c);
    }

    public static List<?> parseJsonList(String json, Type type) {
        Gson gson = new Gson();
        return ((List<?>) gson.fromJson(json, type));
    }
}