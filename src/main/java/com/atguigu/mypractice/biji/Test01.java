package com.atguigu.mypractice.biji;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.biji
 * @Author: zhanglang
 * @CreateTime: 2020-10-14 17:09
 * @Description:
 */
public class Test01 {
    public static void main(String[] args) {
        String str1 = "微信公众号菜鸟名企梦\n干货多多";
        System.out.println(str1);
        System.out.println("----------------------------------------");

        str1 =  "微信公众号菜鸟名企梦\r干货多多";
        System.out.println(str1);
        System.out.println("----------------------------------------");


        str1 =  "微信公众号菜鸟名企梦\r\n干货多多";
        System.out.println(str1);
        System.out.println("----------------------------------------");



    }
}
