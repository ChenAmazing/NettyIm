package com.myself.serverhandler;

import com.myself.request.MessageRequestPacket;
import com.myself.response.MessageResponsePacket;
import com.myself.session.Session;
import com.myself.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        // 1.拿到消息发送方的会话信息
        Session session = SessionUtil.getSession(ctx.channel());
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());
        // 3.拿到消息接收方的 channel
        Channel channel = SessionUtil.getChannel(messageRequestPacket.getToUserId());
        // 4.将消息发送给消息接收方
        if (channel != null && SessionUtil.hasLogin(channel)) {
            channel.writeAndFlush(messageResponsePacket);
        } else {
            System.err.println("[" + messageRequestPacket.getToUserId() + "] 不在线，发送失败!");
        }
    }
}