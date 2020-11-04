package com.atguigu.mypractice.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.netty.http
 * @Author: zhanglang
 * @CreateTime: 2020-10-26 10:03
 * @Description:
 */
public class NettyHttpInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 得到管道
        ChannelPipeline pipeline = ch.pipeline();

        // 编码解码器
        pipeline.addLast("MyHttpServerCodec",new HttpServerCodec());
        // 增加一个自定义handler
        pipeline.addLast("MyHttpServerHandler",new MyHttpServerHandler());
    }
}
