package com.atguigu.mypractice.netty.handlershunxu;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.netty.handlershunxu
 * @Author: zhanglang
 * @CreateTime: 2020-10-27 10:13
 * @Description:
 */
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;

public class InboundHandler2 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf= (ByteBuf) msg;
        System.out.println("inbound2  === "+byteBuf.toString(CharsetUtil.UTF_8));
        //ctx.fireChannelRead(Unpooled.copiedBuffer("inbound2===>>>>"+byteBuf.toString(CharsetUtil.UTF_8),CharsetUtil.UTF_8));
        ctx.writeAndFlush(Unpooled.copiedBuffer("inbound2===>>>>"+byteBuf.toString(CharsetUtil.UTF_8),CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
