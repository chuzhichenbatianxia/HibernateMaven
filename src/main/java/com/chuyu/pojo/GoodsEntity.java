package com.chuyu.pojo;

import javax.persistence.*;

/**
 * Created by Administrator on 2017-08-09.
 */
@Entity
@Table(name = "goods", schema = "hibernate", catalog = "")
public class GoodsEntity {
    private int sid;
    private String name;
    private Double price;

    public GoodsEntity() {
    }

    public GoodsEntity(int sid, String name, Double price) {
        this.sid = sid;
        this.name = name;
        this.price = price;
    }

    @Id
    @Column(name = "sid")
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
