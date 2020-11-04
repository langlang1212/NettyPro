package com.atguigu.mypractice.netty.nianchaibaojiejue;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.netty.nianchaibaojiejue
 * @Author: zhanglang
 * @CreateTime: 2020-10-28 09:52
 * @Description:
 */
public class TcpBaoJjServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    private int count;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        // 接收到数据，并处理
        int len = msg.getLen();
        byte[] content = msg.getContent();

        System.out.println("服务端接收到的消息如下:=====================================");
        System.out.println("长度:"+len);
        System.out.println("内容:"+new String(content, CharsetUtil.UTF_8));
        System.out.println("服务端接收到的消息包数量:"+(++count));

        // 回复消息
        String responseContext = UUID.randomUUID().toString();
        int responseLen = responseContext.getBytes("utf-8").length;
        byte[] responseContent = responseContext.getBytes("utf-8");
        // 构建一个协议包
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(responseLen);
        messageProtocol.setContent(responseContent);
        ctx.writeAndFlush(messageProtocol);

    }
}
