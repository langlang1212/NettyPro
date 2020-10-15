package com.atguigu.mypractice.bio;

/**
 * @BelongsProject: NettyPro
 * @BelongsPackage: com.atguigu.mypractice.bio
 * @Author: zhanglang
 * @CreateTime: 2020-10-12 20:00
 * @Description: 变种版的建造者模式
 */
public class BuildEntity {
    private int id;

    private String name;

    private BuildEntity(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Builder{
        private int id;

        private String name;

        public Builder id(int id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public BuildEntity build(){
            return new BuildEntity(this);
        }

    }
}
