package com.atguigu.thread;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.thread
 * @Author: zhanglang
 * @CreateTime: 2020-11-03 12:57
 * @Description:
 */
public class MyThread extends Thread {
    private HandleService handleService;
    public MyThread(HandleService service){
        this.handleService = service;
    }


    @Override
    public void run() {
        handleService.say();
    }
}
