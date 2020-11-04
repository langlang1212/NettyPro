package com.atguigu.mypractice.netty.nianchaibaojiejue;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.netty.nianchaibaojiejue
 * @Author: zhanglang
 * @CreateTime: 2020-10-28 09:53
 * @Description:
 */
public class TcpBaoJjClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 客户端发送10条，"今天天气冷，吃火锅" 编号
        for(int i =0 ;i<10 ;i++){
            String info = "今天天气冷，吃火锅";
            byte[] content = info.getBytes(CharsetUtil.UTF_8);
            int length = info.getBytes(CharsetUtil.UTF_8).length;
            // 创建协议包对象
            MessageProtocol messageProtocol = new MessageProtocol();
            messageProtocol.setLen(length);
            messageProtocol.setContent(content);

            ctx.writeAndFlush(messageProtocol);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        int len = msg.getLen();
        byte[] content = msg.getContent();
        System.out.println("客户端收到的消息如下:=========================");
        System.out.println("消息长度:"+len);
        System.out.println("消息内容:"+new String(content,CharsetUtil.UTF_8));
        System.out.println("客户端收到消息数量:"+(++count));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常消息:"+cause.getMessage());
        ctx.close();
    }
}
