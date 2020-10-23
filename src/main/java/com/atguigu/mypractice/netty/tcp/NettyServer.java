package com.atguigu.mypractice.netty.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.netty.tcp
 * @Author: zhanglang
 * @CreateTime: 2020-10-23 09:55
 * @Description:
 */
public class NettyServer {
    public static void main(String[] args) {
        // 创建BossGroup和WorkGroup
        // 创建bossGroup 负责连接请求，nThreads 代表线程数(NioEventLoop个数)
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 线程数:默认CPU核数*2 workGroup负责读写请求
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
        // 服务端启动参数
        ServerBootstrap bootstrap = new ServerBootstrap();
        // 使用链式编程来设置
        bootstrap.group(bossGroup,workGroup)
                 .channel(NioServerSocketChannel.class)  //使用NioSocketChannel作为服务器的通道实现
                 .option(ChannelOption.SO_BACKLOG,128) // 设置线程队列得到连接个数
                 .childOption(ChannelOption.SO_KEEPALIVE,true)  // 设置保持活动连接状态
                 .childHandler(new ChannelInitializer<SocketChannel>() {
                     // 给pipeline设置处理器
                     @Override
                     protected void initChannel(SocketChannel ch) throws Exception {
                        //ch.pipeline().addLast(new NettyServerHandler());
                         ch.pipeline().addLast(new NettyServerHandlerTask());
                     }
                 })  // 给workGroup的NioEventLoop对应的通道设置处理器
        ;
        System.out.println("...服务器 is ready...");

        // 绑定一个端口并且同步,生成一个ChannelFuture对象
            ChannelFuture cf = bootstrap.bind(6668).sync();
            cf.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(future.isSuccess()){
                        System.out.println("连接成功...");
                    }else{
                        System.out.println("连接失败...");
                    }
                }
            });
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
