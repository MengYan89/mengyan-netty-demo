package org.mengyan.netty.util;

import com.google.gson.Gson;

public final class JsonUtil {
    private static final Gson GSON = new Gson();
    private JsonUtil() {

    }

    public static <T> T fromJson(String strJson, Class<T> clazz) {
        return GSON.fromJson(strJson, clazz);
    }

    public static String toJson(Object object) {
        return GSON.toJson(object);
    }
}
