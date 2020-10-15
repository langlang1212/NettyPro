package com.atguigu.mypractice.bio;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.bio
 * @Author: zhanglang
 * @CreateTime: 2020-10-12 20:04
 * @Description:
 */
public class TestBuild {
    public static void main(String[] args) {
        BuildEntity buildEntity = new BuildEntity.Builder().id(1).name("张三").build();
    }
}
