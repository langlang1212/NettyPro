package com.atguigu.mypractice.netty.groupchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.netty.groupchat
 * @Author: zhanglang
 * @CreateTime: 2020-10-26 15:34
 * @Description:
 */
public class GroupChatClient {

    private final String host;

    private final int port;

    public GroupChatClient(String host,int port){
        this.host = host;

        this.port = port;
    }

    public void run() throws Exception{

        EventLoopGroup group = new NioEventLoopGroup();

        try{
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

                            pipeline.addLast("decoder",new StringDecoder());
                            pipeline.addLast("encoder",new StringEncoder());
                            pipeline.addLast(new GroupChatClientHandler());
                        }
                    })
                    ;
            ChannelFuture cf = bootstrap.connect(host, port).sync();
            // 得到channel
            Channel channel = cf.channel();
            System.out.println("--------------"+channel.localAddress()+"-----------------------");
            // 输入信息
            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNextLine()){
                String info = scanner.nextLine();
                // 发送给服务端
                channel.writeAndFlush(info+"\r\n");
            }
            cf.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new GroupChatClient("127.0.0.1",7000).run();
    }
}
