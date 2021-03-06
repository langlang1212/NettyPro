package com.atguigu.mypractice.nio.zerocopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.nio.zerocopy
 * @Author: zhanglang
 * @CreateTime: 2020-10-22 09:20
 * @Description:
 */
public class NewIoServer {
    public static void main(String[] args) throws Exception{

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        ServerSocket serverSocket = serverSocketChannel.socket();

        serverSocket.bind(new InetSocketAddress(7001));

        // 创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

        while(true){
            // 获取一个连接到服务端的通道
            SocketChannel socketChannel = serverSocketChannel.accept();

            int readCount = 0;
            while(-1 != readCount){
                try{
                    readCount = socketChannel.read(byteBuffer);
                }catch (Exception e){
                    e.printStackTrace();
                    break;
                }
                byteBuffer.rewind();// 倒带 position =0 remark作废
            }
        }

    }
}
