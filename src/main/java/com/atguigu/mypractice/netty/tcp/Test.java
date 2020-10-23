package com.atguigu.mypractice.netty.tcp;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.netty.tcp
 * @Author: zhanglang
 * @CreateTime: 2020-10-23 15:55
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        /*System.out.println("主线程..");

        new Thread(()->{
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("主线程 end....");*/
        long a = 1000l;
        long b = 1000l;
        Long c = 1000l;
        Long d = 1000l;
        System.out.println(a == b);
        System.out.println(c == d);
    }
}
