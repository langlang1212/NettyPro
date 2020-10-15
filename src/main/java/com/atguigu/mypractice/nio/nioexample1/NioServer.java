package com.atguigu.mypractice.nio.nioexample1;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.nio.nioexample1
 * @Author: zhanglang
 * @CreateTime: 2020-10-12 19:05
 * @Description:
 */
public class NioServer {
    public static void main(String[] args) throws IOException {
        // 创建一个服务端channel ---> ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 创建一个Selector
        Selector selector = Selector.open();
        // 绑定6666端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 把serverSocketChannel感兴趣的时间注册到selector上面
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while(true){
            // 查询有事件发生的个数
            if(selector.select(1000) == 0){
                System.out.println("服务器等待了1秒...,无连接");
                continue;
            }
            // 拿到关注事件的key的集合
            Set<SelectionKey> keySet = selector.selectedKeys();
            // 遍历
            Iterator<SelectionKey> iterator = keySet.iterator();

            while(iterator.hasNext()){
                // 获取到SelectionKey
                SelectionKey key = iterator.next();
                // 根据key对应的通道的事件做相应的处理
                if(key.isAcceptable()){ // 代表有客户端连接
                    // 该客户端生成一个SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端"+socketChannel.socket().getInetAddress()+"连接成功,并生成一个SocketChannel:"+socketChannel.hashCode());
                    // 设置为非阻塞
                    socketChannel.configureBlocking(false);
                    // 把客户端SocketChannel注册到selector
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if(key.isReadable()){ // 如果发生读事件
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                    channel.read(byteBuffer);
                    System.out.println(new String(byteBuffer.array()));
                }
                // 手动从iterator移除这个key
                iterator.remove();
            }
        }
    }
}
