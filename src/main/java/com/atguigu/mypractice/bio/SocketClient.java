package com.atguigu.mypractice.bio;

import jdk.internal.util.xml.impl.Input;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.bio
 * @Author: zhanglang
 * @CreateTime: 2020-10-12 15:38
 * @Description:
 */
public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",9999);
        String str = "hello";
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(str.getBytes());

        byte[] bytes = new byte[1024];
        int read = inputStream.read(bytes);
        if(read != -1){
            System.out.println(new String(bytes,0,read));
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }
}
