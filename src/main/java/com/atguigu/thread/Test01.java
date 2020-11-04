package com.atguigu.thread;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.thread
 * @Author: zhanglang
 * @CreateTime: 2020-11-03 12:57
 * @Description:
 */
public class Test01 {
    public static void main(String[] args) {
        HandleService service = new HandleService();
        for(int i =0 ;i<100;i++){
            MyThread thread = new MyThread(service);
            thread.start();
        }
    }
}
