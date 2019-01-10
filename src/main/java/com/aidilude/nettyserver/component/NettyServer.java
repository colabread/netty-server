package com.aidilude.nettyserver.component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    public static int port = 8088;

    public static void main(String[] args) throws Exception {
        //boss线程组，用于接受客户端的连接，并调度worker去处理任务
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //worker线程组，用于处理boss线程组分配的任务
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //netty服务器的启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)   //指定线程组
                    .channel(NioServerSocketChannel.class)  //指定信道类型
                    .childHandler(new HelloNettyInitializer());                    //指定worker线程组的处理器

            //绑定端口并以同步的方式启动netty服务
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

            //监听关闭的channel，设置为同步的方式
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

}
