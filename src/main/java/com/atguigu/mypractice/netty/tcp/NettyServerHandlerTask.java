package com.atguigu.mypractice.netty.tcp;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.netty.tcp
 * @Author: zhanglang
 * @CreateTime: 2020-10-23 11:14
 * @Description:
 */
public class NettyServerHandlerTask extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(5000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端,喵1", CharsetUtil.UTF_8));
                    System.out.println("channel code="+ctx.channel().hashCode());
                }catch (Exception e){
                    System.out.println("发生异常:"+e.getMessage());
                }
            }
        });

        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(5000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端,喵2", CharsetUtil.UTF_8));
                    System.out.println("channel code="+ctx.channel().hashCode());
                }catch (Exception e){
                    System.out.println("发生异常:"+e.getMessage());
                }
            }
        });

        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(5000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端,喵3", CharsetUtil.UTF_8));
                    System.out.println("channel code="+ctx.channel().hashCode());
                }catch (Exception e){
                    System.out.println("发生异常:"+e.getMessage());
                }
            }
        },5, TimeUnit.SECONDS);

        System.out.println("go on...");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端,喵5",CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
