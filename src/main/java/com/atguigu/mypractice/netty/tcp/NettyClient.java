package com.atguigu.mypractice.netty.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.netty.tcp
 * @Author: zhanglang
 * @CreateTime: 2020-10-23 10:33
 * @Description:
 */
public class NettyClient {
    public static void main(String[] args) {

        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        try {
        bootstrap.group(group)  // 设置线程组
                .channel(NioSocketChannel.class)  //设置通道的实现类
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new NettyClientHandler());
                    }
                });
        System.out.println("客户端OK....");

        // 连接服务端

            ChannelFuture cf = bootstrap.connect("127.0.0.1",6669).sync();  // sync()的作用是连接过后才能结束线程
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }
}
