package com.myself.attribute;

import io.netty.util.AttributeKey;

public interface attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("Login");
}
