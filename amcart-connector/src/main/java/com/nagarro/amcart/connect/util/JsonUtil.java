package com.nagarro.amcart.connect.util;

import com.google.gson.Gson;

public class JsonUtil {

    public static <T> T fromJson(final String json, final Class<T> classOfT) {
        final Gson gson = new Gson();
        T instance = gson.fromJson(json, classOfT);
        return instance;
    }
}
