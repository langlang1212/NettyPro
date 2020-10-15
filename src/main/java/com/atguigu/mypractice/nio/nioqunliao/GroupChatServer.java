package com.atguigu.mypractice.nio.nioqunliao;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.nio.nioqunliao
 * @Author: zhanglang
 * @CreateTime: 2020-10-14 10:08
 * @Description:
 */
public class GroupChatServer {

    private Selector selector;

    private ServerSocketChannel listenChannel;

    private static final int PORT = 6667;

    // 构造器
    // 初始化
    public GroupChatServer(){
        try {
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            // 绑定端口
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            // 设置成非阻塞模式
            listenChannel.configureBlocking(false);
            // 注册listenChannel到selector,关注连接事件
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 监听
    public void listen(){
        try{
            while(true){
                int count = selector.select();
                if(count > 0){ // 有事件处理
                    // 拿到有事件发生的SelectionKey
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while(iterator.hasNext()){
                        SelectionKey key = iterator.next();

                        if(key.isAcceptable()){ // 如果监听到连接事件
                            // 拿到客户端的SocketChannel
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            // 设置为非阻塞
                            socketChannel.configureBlocking(false);
                            // 把拿到的客户端channel注册到selector，并关注读取事件
                            socketChannel.register(selector,SelectionKey.OP_READ);
                            // 提示
                            System.out.println(socketChannel.getRemoteAddress()+"上线!");
                        }
                        if(key.isReadable()){ // 接收到读取事件,即通道可读(通道里面有数据)
                            // 处理读，专门写个方法处理读事件
                            readData(key);
                        }
                        // 删除key，以免重复处理
                        iterator.remove();
                    }
                }else{
                    System.out.println("等待.....");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // 读取客户端信息方法
    private void readData(SelectionKey key) {
        // 取到关联的SocketChannel
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) key.channel();
            // 创建Buffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int count = channel.read(byteBuffer);
            if(count > 0){
                // 把缓冲区的数据转成字符串
                String str = new String(byteBuffer.array());
                // 输出消息
                System.out.println("from 客户端:"+str);
                // 向其他客户端转发自己收到的消息
                sendInfoToOtherClients(str,channel);
            }
        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress()+"离线了...");
                // 取消注册
                key.cancel();
                // 关闭通道
                channel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void sendInfoToOtherClients(String str, SocketChannel self) throws IOException {
        System.out.println("服务消息转发中...");
        // 遍历所有注册到selector的SocketChannel,并排除self
        Set<SelectionKey> keys = selector.keys();
        for(SelectionKey key : keys){
            // 通过key拿到对应的SocketChannel
            Channel targetChannel = (SocketChannel) key.channel();
            // 排除自己
            if(targetChannel instanceof SocketChannel && targetChannel != self){
                // 转型
                SocketChannel dest = (SocketChannel) targetChannel;
                // 将msg 存储到buffer
                ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
                // 将buffer数据写入通道
                dest.write(buffer);
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer chatServer = new GroupChatServer();
        chatServer.listen();
    }
}
