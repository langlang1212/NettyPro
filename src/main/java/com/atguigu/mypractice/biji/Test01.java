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
        TestCond cond = new TestCond();
        cond.setCode("10001");
        TestCond1 cond1 = new TestCond1();
        cond1.setName(cond.getName());
        System.out.println(cond1);
    }
}
