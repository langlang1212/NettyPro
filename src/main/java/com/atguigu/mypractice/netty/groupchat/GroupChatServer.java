package com.atguigu.mypractice.netty.groupchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.netty.groupchat
 * @Author: zhanglang
 * @CreateTime: 2020-10-26 14:37
 * @Description:
 */
public class GroupChatServer {

    private int port; // 监听端口

    public GroupChatServer(int port){
        this.port = port;
    }

    // 编写run方法，处理客户端请求
    public void run() throws Exception{
        // 创建两个线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // 默认为CPU核数*2

        try{
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 获取到pipeline
                            ChannelPipeline pipeline = ch.pipeline();
                            // 加入解码器
                            pipeline.addLast("decoder",new StringDecoder());
                            // 加入编码器
                            pipeline.addLast("encoder",new StringEncoder());
                            // 加入业务处理handler
                            pipeline.addLast(new GroupChatServerHandler());
                        }
                    })
                    ;

            System.out.println("启动服务端....");
            ChannelFuture cf = bootstrap.bind(port).sync();
            // 关闭监听
            cf.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new GroupChatServer(7000).run();

    }
}
