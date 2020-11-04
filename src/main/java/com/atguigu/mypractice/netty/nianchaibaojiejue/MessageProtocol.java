package com.atguigu.mypractice.netty.nianchaibaojiejue;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.netty.nianchaibaojiejue
 * @Author: zhanglang
 * @CreateTime: 2020-10-28 09:49
 * @Description:
 * 解决黏包拆包
 * 1、自定义协议+编解码器
 * 2、关键就是解决服务端每次读取数据的长度，读取过后，就会解决每次多读或者少读的问题
 */
public class MessageProtocol {

    private int len;

    private byte[] content;

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
