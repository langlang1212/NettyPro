package com.atguigu.mypractice.thread;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.thread
 * @Author: zhanglang
 * @CreateTime: 2020-10-30 17:18
 * @Description:
 */
class Foo {

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

    public static void main(String[] args) throws Exception{
        Runnable r1 = ()->{
            System.out.print("first");
        } ;
        Runnable r2 = ()->{
            System.out.print("second");
        } ;
        Runnable r3 = ()->{
            System.out.print("third");
        } ;

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
    }
}