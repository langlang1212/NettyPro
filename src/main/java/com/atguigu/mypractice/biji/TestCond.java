package com.atguigu.mypractice.biji;

import java.util.concurrent.locks.Condition;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.biji
 * @Author: zhanglang
 * @CreateTime: 2020-10-14 17:08
 * @Description:
 */
public class TestCond extends Cond {
    private String code;

    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
