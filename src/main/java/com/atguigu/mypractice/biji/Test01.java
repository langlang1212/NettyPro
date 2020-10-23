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
        Cond cond = new Cond();
        cond.setCode("1");
        cond.setName("zhangsan");
        TestCond cond1 = (TestCond) cond;
        System.out.println(cond1);


    }
}
