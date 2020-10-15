package com.atguigu.mypractice.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.bio
 * @Author: zhanglang
 * @CreateTime: 2020-10-12 15:34
 * @Description:
 */
public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务端正在等待连接...");
        Socket socket = serverSocket.accept();
        System.out.println("客户端:"+socket.getInetAddress()+"已经连接@");
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        byte[] bytes = new byte[1024];
        int read = inputStream.read(bytes);
        if(read  != -1){
            System.out.println(new String(bytes,0,read));
        }
        outputStream.write("已经收到客户端信息...".getBytes());
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }
}
