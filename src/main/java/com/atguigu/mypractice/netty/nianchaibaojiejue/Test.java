package com.atguigu.mypractice.netty.nianchaibaojiejue;

import io.netty.util.CharsetUtil;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.netty.nianchaibaojiejue
 * @Author: zhanglang
 * @CreateTime: 2020-10-28 10:37
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        String info = "今天天气冷，吃火锅";
        byte[] content = info.getBytes(CharsetUtil.UTF_8);
        int length = info.getBytes(CharsetUtil.UTF_8).length;
        System.out.println(length);
    }
}
