package com.atguigu.mypractice.netty.nianbaochaibao;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.netty.nianbaochaibao
 * @Author: zhanglang
 * @CreateTime: 2020-10-27 14:41
 * @Description:
 */
public class TcpBaoServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int count;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] bytes = new byte[msg.readableBytes()];

        msg.readBytes(bytes);

        String message = new String(bytes, CharsetUtil.UTF_8);

        System.out.println("服务端接收到的数据:"+message);
        System.out.println("服务端接收到的数量:"+(++this.count));

        ByteBuf responseBuffer = Unpooled.copiedBuffer(UUID.randomUUID().toString()+ " ",CharsetUtil.UTF_8);
        ctx.writeAndFlush(responseBuffer);
    }
}
