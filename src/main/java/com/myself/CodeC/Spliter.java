package com.myself.CodeC;

import com.myself.command.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

//拒绝非本协议连接
public class Spliter extends LengthFieldBasedFrameDecoder {
    private static final int lengthFieldOffset = 7;
    private static final int lengthFieldLength = 4;

    public Spliter() {
        super(Integer.MAX_VALUE, lengthFieldOffset, lengthFieldLength);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER) {
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx, in);
    }
}
