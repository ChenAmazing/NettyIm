package com.myself.clienthandler;

import com.myself.response.LogoutResponsePacket;
import com.myself.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutResponsePacket logoutResponsePacket) throws Exception {
        SessionUtil.unBindSession(channelHandlerContext.channel());
    }
}
