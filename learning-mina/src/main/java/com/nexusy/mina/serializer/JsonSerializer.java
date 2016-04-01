package com.nexusy.mina.serializer;

import com.nexusy.mina.json.JsonMapper;
import com.nexusy.mina.pack.Data;

/**
 * @author lan
 * @since 2016-04-01
 */
public class JsonSerializer<T> implements Serializer<T> {


    @Override
    public Data serialize(T object, Class<T> clazz) {
        System.out.println("use jackson json serialize data.");
        Data data = new Data();
        data.setContentType(0);
        data.setContent(JsonMapper.writeValueAsBytes(object));
        return data;
    }

    @Override
    public T deserialize(Data data, Class<T> clazz) {
        System.out.println("use jackson json deserialize data.");
        return JsonMapper.readValue(data.getContent(), clazz);
    }
}
