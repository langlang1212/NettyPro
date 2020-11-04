package com.atguigu.mypractice.netty.nianchaibaojiejue;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.netty.nianchaibaojiejue
 * @Author: zhanglang
 * @CreateTime: 2020-10-28 10:06
 * @Description:
 */
public class MyMessageDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyMessageDecoder decode 方法被调用");
        // 需要将二进制字节码转为 MessageProtocol对象
        int length = in.readInt();
        byte[] content = new byte[length];
        in.readBytes(content);

        // 封装成MessageProtocol对象，放入out，传递给下一个handler
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(length);
        messageProtocol.setContent(content);
        out.add(messageProtocol);
    }
}
