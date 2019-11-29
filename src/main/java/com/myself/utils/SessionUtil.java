package com.myself.utils;

import com.myself.attribute.attributes;
import com.myself.session.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {
    private static ConcurrentHashMap<String, Channel> userIdMap = new ConcurrentHashMap<String, Channel>();
    private static ConcurrentHashMap<String, ChannelGroup> groupIdChannelGroupMap = new ConcurrentHashMap<String, ChannelGroup>();

    //客户端的bindsession和服务端的bindsession会使userIdMap中的channel发生改变
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

    public static ChannelGroup getChannelGroup(String groupId) {
        return groupIdChannelGroupMap.get(groupId);
    }

    public static void bindChannelGroup(String groupId, ChannelGroup channelGroup) {
        groupIdChannelGroupMap.put(groupId, channelGroup);
    }

}
