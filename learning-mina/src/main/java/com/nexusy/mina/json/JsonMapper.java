package com.nexusy.mina.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

/**
 * @author lan
 * @since 2015-08-12
 */
public class JsonMapper {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private JsonMapper() {
    }

    public static byte[] writeValueAsBytes(Object object) {
        byte[] data = null;
        try {
            data = MAPPER.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static <T> T readValue(byte[] json, Class<T> clazz) {
        T t = null;
        if (json != null && json.length != 0) {
            try {
                t = MAPPER.readValue(json, clazz);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return t;
    }

}
