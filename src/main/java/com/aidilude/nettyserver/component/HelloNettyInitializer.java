package com.aidilude.nettyserver.component;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * channel初始化器
 * 用于给channel添加助手类
 */
public class HelloNettyInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel channel) throws Exception {
        //获取channel中的管道pipeline
        ChannelPipeline pipeline = channel.pipeline();

        //在管道的末尾添加助手类
        //netty自带的助手类，用于对解码请求，编码响应
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        //添加自定义的助手类
        pipeline.addLast("customHandler", new CustomHandler());
    }
}
