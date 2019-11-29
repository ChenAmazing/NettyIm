package com.myself.serverhandler;

import com.myself.request.ListGroupMembersRequestPacket;
import com.myself.response.ListGroupMembersResponsePacket;
import com.myself.session.Session;
import com.myself.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;

public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ListGroupMembersRequestPacket listGroupMembersRequestPacket) throws Exception {
        // 1. 获取群的 ChannelGroup
        String groupId = listGroupMembersRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        ArrayList<Session> sessionList = new ArrayList<>();

        // 2. 遍历群成员的 channel，对应的 session，构造群成员的信息
        for(Channel channel : channelGroup){
            Session session = SessionUtil.getSession(channel);
            sessionList.add(session);

        }
        ListGroupMembersResponsePacket listGroupMembersResponsePacket = new ListGroupMembersResponsePacket();
        listGroupMembersResponsePacket.setSessionList(sessionList);
        listGroupMembersResponsePacket.setGroupId(groupId);
        channelHandlerContext.channel().writeAndFlush(listGroupMembersResponsePacket);
    }
}
