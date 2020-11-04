package com.atguigu.mypractice.netty.handlershunxu;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.netty.handlershunxu
 * @Author: zhanglang
 * @CreateTime: 2020-10-27 10:14
 * @Description:
 */
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import jdk.nashorn.internal.runtime.linker.Bootstrap;

public class EchoServer {

    private int port;
    NioEventLoopGroup boss=null;
    NioEventLoopGroup work=null;

    public EchoServer(int port) {
        this.port = port;
    }


    public void run() {

        try {
            boss = new NioEventLoopGroup();
            work = new NioEventLoopGroup();
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, work).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel channel) throws Exception {
                            ChannelPipeline pipeline = channel.pipeline();
//                            pipeline.addLast(new EchoServerhandler());

                            /**
                             * 如果
                             * pipeline.addLast(new OutboundHandler1());
                             * pipeline.addLast(new OutboundHandler2());
                             * 在
                             * pipeline.addLast(new InboundHandler1());
                             * pipeline.addLast(new InboundHandler2());
                             * 后面，最后一个InboundHandler用的ctx.writeAndFlush(),则不进入OutboundHandler
                             *
                             * ctx.fireChannelRead与 ctx.writeAndFlush的区别
                             * 前者是只会向下一个handler传递数据，不会返回到客户端，后者是只会返回到客户端，不会向下一个handler传递数据。
                             * 出站是反向的，ctx.writeAndFlush 从链的后面查找前面的OutboundHandler
                             *
                             * ctx.channel().writeAndFlush
                             * ctx.pipeline().writeAndFlush
                             * ctx.writeAndFlush的区别，ctx.writeAndFlush不会去执行之前的handeler,只会执行之后的hadler,,而前面两个会从头执行handler.
                             */

                            pipeline.addLast(new OutboundHandler1());
                            pipeline.addLast(new OutboundHandler2());
                            pipeline.addLast(new InboundHandler1());
                            pipeline.addLast(new InboundHandler2());
                        }
                    });


            System.out.println("echo 启动成功，端口号：" + port);
            //绑定端口，同步阻塞成功
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            //等待服务端监听端口关闭
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            ///关闭资源
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }


    }

    public static void main(String[] args) {
        int port = 8080;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        new EchoServer(port).run();


    }
}
