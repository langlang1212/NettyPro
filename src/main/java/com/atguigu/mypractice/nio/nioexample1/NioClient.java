package com.atguigu.mypractice.nio.nioexample1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.nio.nioexample1
 * @Author: zhanglang
 * @CreateTime: 2020-10-12 19:06
 * @Description:
 */
public class NioClient {
    public static void main(String[] args) throws IOException {
        // 得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        // 设置为非阻塞
        socketChannel.configureBlocking(false);
        // 提供服务端的ip和端口
        InetSocketAddress inetAddress = new InetSocketAddress("127.0.0.1",6666);
        // 连接服务器
        if(!socketChannel.connect(inetAddress)){
            while(!socketChannel.finishConnect()){
                System.out.println("因为连接需要事件，客户端不会阻塞，可以做其他工作....");
            }
        }

        // 连接成功就发送数据
        String str = "hello!";
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
        // 发送数据，将byteBuffer的数据写入buffer
        socketChannel.write(byteBuffer);
        //System.in.read();
    }
}
