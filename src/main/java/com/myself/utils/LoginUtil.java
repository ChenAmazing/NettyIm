package com.myself.utils;

import com.myself.attribute.attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;


public class LoginUtil {
    public static void markAsLogin(Channel channel){
         channel.attr(attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(attributes.LOGIN);
        return loginAttr.get() != null;
    }
}
