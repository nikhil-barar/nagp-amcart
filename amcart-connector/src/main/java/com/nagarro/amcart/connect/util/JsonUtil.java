package com.nagarro.amcart.connect.util;

import com.google.gson.Gson;

/**
 * The Class JsonUtil.
 */
public class JsonUtil {

    /** The Constant GSON. */
    private static final Gson GSON = new Gson();

    /**
     * From json.
     *
     * @param <T>
     *            the generic type
     * @param json
     *            the json
     * @param classOfT
     *            the class of T
     * @return the t
     */
    public static <T> T fromJson(final String json, final Class<T> classOfT) {
        T instance = GSON.fromJson(json, classOfT);
        return instance;
    }

    /**
     * To json.
     *
     * @param <T>
     *            the generic type
     * @param object
     *            the object
     * @return the string
     */
    public static <T> String toJson(T object) {
        return GSON.toJson(object);
    }
}
