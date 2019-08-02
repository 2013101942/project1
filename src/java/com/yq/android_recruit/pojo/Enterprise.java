package com.yq.android_recruit.pojo;

import java.io.Serializable;

public class Enterprise implements Serializable {
    private String id, e_name, e_property, e_scale, e_city, e_addr,e_tel, e_info;
    private boolean isFreeze;


    public Enterprise() {
    }

    public Enterprise(String id, String e_name, String e_property, String e_scale, String e_city, String e_addr, String e_info, boolean isFreeze) {
        this.id = id;
        this.e_name = e_name;
        this.e_property = e_property;
        this.e_scale = e_scale;
        this.e_city = e_city;
        this.e_addr = e_addr;
        this.e_info = e_info;
        this.isFreeze = isFreeze;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getE_property() {
        return e_property;
    }

    public void setE_property(String e_property) {
        this.e_property = e_property;
    }

    public String getE_scale() {
        return e_scale;
    }

    public void setE_scale(String e_scale) {
        this.e_scale = e_scale;
    }

    public String getE_city() {
        return e_city;
    }

    public void setE_city(String e_city) {
        this.e_city = e_city;
    }

    public String getE_addr() {
        return e_addr;
    }

    public void setE_addr(String e_addr) {
        this.e_addr = e_addr;
    }

    public String getE_tel() {
        return e_tel;
    }

    public void setE_tel(String e_tel) {
        this.e_tel = e_tel;
    }

    public String getE_info() {
        return e_info;
    }

    public void setE_info(String e_info) {
        this.e_info = e_info;
    }

    public boolean isFreeze() {
        return isFreeze;
    }

    public void setFreeze(boolean freeze) {
        isFreeze = freeze;
    }
}
