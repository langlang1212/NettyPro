package com.atguigu.mypractice.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.netty.tcp
 * @Author: zhanglang
 * @CreateTime: 2020-10-23 10:12
 * @Description:
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务器读取线程:"+Thread.currentThread().getName());
        System.out.println("server ctx="+ctx);
        System.out.println("看看channel和pipeline的关系....");
        Channel channel = ctx.channel();
        ChannelPipeline pipeline = channel.pipeline();  // 本质是一个双向连接 出站和入站

        // 将msg转换成一个ByteBuffer
        ByteBuf byteBuffer = (ByteBuf) msg;
        System.out.println("客户端发送的消息是:"+byteBuffer.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址:"+channel.remoteAddress());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端",CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
