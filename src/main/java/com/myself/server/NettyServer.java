package com.myself.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

public class NettyServer {
    private static final int BEGIN_PORT = 8000;
    public static void main(String[] args) {
        //创建接收连接和读写的线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        final AttributeKey<Object> clientKey = AttributeKey.newInstance("clientKey");
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workGroup)
                //指定io模型
                .channel(NioServerSocketChannel.class)
                //给服务端的连接指定属性
//                .attr(AttributeKey.newInstance("serverName"),"NettyServer")
//                .handler(new ChannelInboundHandlerAdapter(){
//                    //这个方法会在客户端连接建立成功之后被调用
//                    @Override
//                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//                        super.channelActive(ctx);
//                    }
//                })
//                .childAttr(clientKey, "clientValue")
                //设置tcp的一些属性
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
//                        ch.pipeline().addLast(new FirstServerHandler());
                        ch.pipeline().addLast(new ServerHandler());
                    }
                });
        bind(serverBootstrap,BEGIN_PORT);
    }
    public static void bind(ServerBootstrap serverBootstrap,final int port){
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("端口[" + port + "]绑定成功!");
            } else {
                System.err.println("端口[" + port + "]绑定失败!");
                bind(serverBootstrap, port + 1);
            }
        });
    }
}
