package com.atguigu.mypractice.nio.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.nio.zerocopy
 * @Author: zhanglang
 * @CreateTime: 2020-10-22 09:58
 * @Description:   把文件传到server
 */
public class NewIoClient {
    public static void main(String[] args) throws Exception{

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",7001));
        String filename = "test.txt";

        // 得到一个文件channel
        FileChannel fileChannel = new FileInputStream(filename).getChannel();

        // 准备发送
        long startTime = System.currentTimeMillis();
        // transferTo使用零拷贝
        long transferCount = fileChannel.transferTo(0,fileChannel.size(),socketChannel);

        fileChannel.close();

    }
}
