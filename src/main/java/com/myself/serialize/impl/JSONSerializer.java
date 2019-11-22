package com.myself.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.myself.serialize.Serializer;
import com.myself.serialize.SerializeAlogrthim;

public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlogrithm() {
        return SerializeAlogrthim.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
