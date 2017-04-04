package com.mytestapp;

/**
 * Created by Administrator on 30/06/2015.
 */
public class Generic {

    private String id;
    private String name;
    private String phone;

    public Generic(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Generic(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
