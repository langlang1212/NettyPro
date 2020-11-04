package com.atguigu.mypractice.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.netty.http
 * @Author: zhanglang
 * @CreateTime: 2020-10-26 10:19
 * @Description:
 * 1、SimpleChannelInboundHandler是ChannelInboundHandlerAdapter
 * 2、HttpObject是客户端和服务端相互通信的数据
 */
public class MyHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        // 如果是个http请求
        if(msg instanceof HttpRequest){
            System.out.println("pipeline hashcode"+ctx.pipeline().hashCode()+"MyHttpServerHandler hash "+this.hashCode());

            System.out.println("msg 类型=" + msg.getClass());
            System.out.println("客户端地址"+ctx.channel().remoteAddress());

            HttpRequest request = (HttpRequest) msg;

            // 获取uri
            URI uri = new URI(request.uri());

            if("/favicon.ico".equals(uri)){
                System.out.println("请求了favicon.ico,不做响应");
                return;
            }
            // 回复信息给浏览器 http协议
            ByteBuf content = Unpooled.copiedBuffer("hello,我是服务器", CharsetUtil.UTF_8);

            // 构造一个http响应
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK,content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

            // 返回
            ctx.writeAndFlush(response);

        }
    }
}
