package com.nexusy.mina.serializer;

import com.nexusy.mina.pack.Data;

/**
 * @author lan
 * @since 2016-04-01
 */
public class AvroSerializer<T> implements Serializer<T> {

    @Override
    public Data serialize(T object, Class<T> clazz) {
        //ToDo
        return null;
    }

    @Override
    public T deserialize(Data data, Class<T> clazz) {
        //Todo
        return null;
    }
}