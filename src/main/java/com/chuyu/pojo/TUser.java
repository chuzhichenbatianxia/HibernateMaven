package com.chuyu.pojo;

/**
 * Created by Administrator on 2017-08-30.
 */
public class TUser {
    private int id;
    private String neme;
    private TGroup group;

    public TGroup getGroup() {
        return group;
    }

    public void setGroup(TGroup group) {
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNeme() {
        return neme;
    }

    public void setNeme(String neme) {
        this.neme = neme;
    }

}
