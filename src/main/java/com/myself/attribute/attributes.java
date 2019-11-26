package com.myself.attribute;

import com.myself.session.Session;
import io.netty.util.AttributeKey;

public interface attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("Login");

    AttributeKey<Session> SESSION = AttributeKey.newInstance("Session");
}
