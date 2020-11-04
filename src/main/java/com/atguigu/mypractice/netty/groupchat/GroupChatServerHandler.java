package com.atguigu.mypractice.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.netty.groupchat
 * @Author: zhanglang
 * @CreateTime: 2020-10-26 14:48
 * @Description:
 */
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    /**
     * 连接被建立第一个执行
     * @param ctx
     * @throws Exception
     * 将当前channel放入channelGroup
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // 将该客户端加入聊天室的信息发送给其他客户端
        channelGroup.writeAndFlush("[客户端]"+channel.remoteAddress()+"加入聊天室..."+sdf.format(new Date())+"\n");
        channelGroup.add(channel);
    }

    /**
     * 客户端断开连接的时候调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]"+channel.remoteAddress()+"断开连接..."+"\n");
        System.out.println("剩余客户端数量:"+channelGroup.size());
    }

    /**
     * 客户端处于活动状态，提示xx上线了
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "上线了..."+"\n");
    }

    /**
     * 离线状态
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "离线了..."+"\n");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 读取数据
        Channel channel = ctx.channel();
        // 遍历channelGroup,根据不同情况，回送不同消息
        channelGroup.forEach(ch -> {
            if(channel != ch){ // 不是当前channel的消息
                ch.writeAndFlush("[客户端]"+channel.remoteAddress()+"发送了消息:"+msg+"\n");
            }else{
                ch.writeAndFlush("[自己]发送了消息:"+msg+"\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
