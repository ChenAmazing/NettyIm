package com.myself.client;

import com.myself.CodeC.PacketDecoder;
import com.myself.CodeC.PacketEncoder;
import com.myself.CodeC.Spliter;
import com.myself.client.console.ConsoleCommandManager;
import com.myself.client.console.LoginConsoleCommand;
import com.myself.clienthandler.CreateGroupResponseHandler;
import com.myself.clienthandler.LoginResponseHandler;
import com.myself.clienthandler.LogoutResponseHandler;
import com.myself.clienthandler.MessageResponseHandler;
import com.myself.command.PacketCodeC;
import com.myself.request.LoginRequestPacket;
import com.myself.request.MessageRequestPacket;
import com.myself.utils.LoginUtil;
import com.myself.utils.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class NettyClient {
    private static final int MAX_RETRY = 5;

    public static void main(String[] args) {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                // 客户端相关的数据读写逻辑是通过handler指定的
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new Spliter());
                        socketChannel.pipeline().addLast(new PacketDecoder());
                        socketChannel.pipeline().addLast(new LoginResponseHandler());
                        socketChannel.pipeline().addLast(new LogoutResponseHandler());
                        socketChannel.pipeline().addLast(new MessageResponseHandler());
                        socketChannel.pipeline().addLast(new CreateGroupResponseHandler());
                        socketChannel.pipeline().addLast(new PacketEncoder());
                    }
                });

        connect(bootstrap,"127.0.0.1",8000,MAX_RETRY);
    }
    //设置重连次数
    private static void connect(Bootstrap bootstrap, String host, int port, int retry){
        bootstrap.connect(host,port).addListener(future -> {
           if(future.isSuccess()){
               System.out.println("连接成功!");
               Channel channel = ((ChannelFuture) future).channel();
               startConsoleThread(channel);
           }else if(retry==0){
               System.out.println("重试次数已用完，放弃连接！");
           }else{
               // 第几次重连
               int order = (MAX_RETRY - retry) + 1;
               // 本次重连的间隔
               int delay = 1 << order;
               System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
               bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                       .SECONDS);
           }
        });
    }

    public static void startConsoleThread(Channel channel){
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        Scanner scanner = new Scanner(System.in);
        new Thread(()->{
            while(!Thread.interrupted()){
                if(!SessionUtil.hasLogin(channel)){
                    loginConsoleCommand.exec(scanner, channel);
                }else{
                    consoleCommandManager.exec(scanner, channel);
                }
            }
        }).start();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
