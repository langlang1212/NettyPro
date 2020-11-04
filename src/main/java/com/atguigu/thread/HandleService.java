package com.atguigu.thread;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.thread
 * @Author: zhanglang
 * @CreateTime: 2020-11-03 12:56
 * @Description:
 */
public class HandleService {
    private int count;
    public void say(){
        synchronized (this){
            count++;
            System.out.println("线程:"+Thread.currentThread()+"count:"+count);
        }
        System.out.println("锁逻辑执行完毕!");
    }
}
