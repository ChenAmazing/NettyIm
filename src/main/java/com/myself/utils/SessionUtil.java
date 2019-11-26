package com.myself.utils;

import com.myself.attribute.attributes;
import com.myself.session.Session;
import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {
    private static ConcurrentHashMap<String, Channel> userIdMap = new ConcurrentHashMap<String, Channel>();

    public static void bindSession(Channel channel, Session session){
        userIdMap.put(session.getUserId(),channel);
        channel.attr(attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel){
        if(hasLogin(channel)){
            userIdMap.remove(getSession(channel).getUserId());
            channel.attr(attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel){
        return channel.hasAttr(attributes.SESSION);
    }

    public static Session getSession(Channel channel){
        return channel.attr(attributes.SESSION).get();
    }

    public static Channel getChannel(String userId){
        return userIdMap.get(userId);
    }
}
