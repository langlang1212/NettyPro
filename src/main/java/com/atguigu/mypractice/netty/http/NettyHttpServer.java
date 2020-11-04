package com.atguigu.mypractice.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.netty.http
 * @Author: zhanglang
 * @CreateTime: 2020-10-26 09:19
 * @Description:
 */
public class NettyHttpServer {
    public static void main(String[] args) throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup)
            .channel(NioServerSocketChannel.class)
            .childHandler(new NettyHttpInitializer())
                    ;

            ChannelFuture cf = bootstrap.bind(6668).sync();
            cf.channel().closeFuture().sync();


        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
