package com.atguigu.mypractice.biji;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.biji
 * @Author: zhanglang
 * @CreateTime: 2020-10-14 17:09
 * @Description:
 */
public class TestCond1 extends Cond {
    private String code;

    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
