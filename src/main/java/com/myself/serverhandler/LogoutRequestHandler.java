package com.myself.serverhandler;

import com.myself.response.LogoutResponsePacket;
import com.myself.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestHandler> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutRequestHandler logoutRequestHandler) throws Exception {
        SessionUtil.unBindSession(channelHandlerContext.channel());
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);
        channelHandlerContext.channel().writeAndFlush(logoutResponsePacket);
    }
}
