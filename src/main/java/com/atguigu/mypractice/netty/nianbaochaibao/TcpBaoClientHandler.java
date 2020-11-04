package com.atguigu.mypractice.netty.nianbaochaibao;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.netty.nianbaochaibao
 * @Author: zhanglang
 * @CreateTime: 2020-10-27 14:53
 * @Description:
 */
public class TcpBaoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i =0 ;i<10;i++){
            ByteBuf buf = Unpooled.copiedBuffer("hello server" + i, CharsetUtil.UTF_8);
            ctx.writeAndFlush(buf);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        String message = new String(bytes,CharsetUtil.UTF_8);
        System.out.println("客户端收到消息:"+message);
        System.out.println("客户端收到消息数量:"+(++this.count));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
