package com.atguigu.mypractice.nio.nioqunliao;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.nio.nioqunliao
 * @Author: zhanglang
 * @CreateTime: 2020-10-14 10:09
 * @Description:
 */
public class GroupChatClient {
    // 定义相关属性
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 6667;
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    // 构造器，完成初始化工作
    public GroupChatClient() throws IOException {
        selector = Selector.open();
        // 连接服务器
        socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",6667));
        // 设置非阻塞
        socketChannel.configureBlocking(false);
        // 将channel注册到selector
        socketChannel.register(selector, SelectionKey.OP_READ);
    }
}
