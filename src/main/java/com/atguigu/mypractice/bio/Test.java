package com.atguigu.mypractice.bio;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.bio
 * @Author: zhanglang
 * @CreateTime: 2020-10-12 16:05
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        A.say();
        B.say();
        A a = new A();
        a.say();
        A b = new B();
        /**
         * 在Java中静态方法可以被继承，但是不能被覆盖，即不能重写。
         * 如果子类中也含有一个返回类型、方法名、参数列表均与之相同的静态方法，那么该子类实际上只是将父类中的该同名方法进行了隐藏，而非重写。
         * 父类引用指向子类对象时，只会调用父类的静态方法。所以，它们的行为也并不具有多态性。
         */
        b.say();
        a.hello();
        b.hello();

        System.out.println(1<<0);
    }
}

class A {
    public static void say(){
        System.out.println("A");
    }

    public void hello(){
        System.out.println("hello A");
    }
}

class B extends A{
    public static void say(){
        System.out.println("B");
    }

    public void hello(){
        System.out.println("hello B");
    }
}

