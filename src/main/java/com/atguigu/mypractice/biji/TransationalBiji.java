package com.atguigu.mypractice.biji;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.biji
 * @Author: zhanglang
 * @CreateTime: 2020-10-13 20:33
 * @Description:
 */
public class TransationalBiji {
    /**
     * @Transactional加在接口上
     * 如果是checked异常 比如 IOException try{}catch(Exception e){ e.printTrack()} 不回滚
     * 如果是uncheckd异常，比如RuntimeException()异常，会回滚。
     */
}
