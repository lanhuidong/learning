package com.nexusy.mina.serializer;

import com.nexusy.mina.pack.Data;

/**
 * @author lan
 * @since 2016-04-01
 */
public interface Serializer<T> {

    Data serialize(T object, Class<T> clazz);

    T deserialize(Data data, Class<T> clazz);

}
