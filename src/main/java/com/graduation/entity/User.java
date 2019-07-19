package com.graduation.entity;

import java.util.Date;

public class User {
    private int user_id;
    private String user_password;
    private String user_phone;
    private String user_power;
    private String user_name;
    private String user_sex;
    private String user_address;
    private Date user_birthday;
    private String user_introduce;
    private String user_image;
    public User(){}
    public User(int user_id, String user_password, String user_phone, String user_power, String user_name, String user_sex, String user_address, Date u_birthday, String user_introduce, String user_image) {
        this.user_id = user_id;
        this.user_password = user_password;
        this.user_phone = user_phone;
        this.user_power = user_power;
        this.user_name = user_name;
        this.user_sex = user_sex;
        this.user_address = user_address;
        this.user_birthday = u_birthday;
        this.user_introduce = user_introduce;
        this.user_image = user_image;
    }
    public User(String user_phone, String user_password) {
        this.user_password = user_password;
        this.user_phone = user_phone;
    }
    public User(String user_phone) {
        this.user_phone = user_phone;
    }
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_power() {
        return user_power;
    }

    public void setUser_power(String user_power) {
        this.user_power = user_power;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public Date getUser_birthday() {
        return user_birthday;
    }

    public void setUser_birthday(Date user_birthday) {
        this.user_birthday = user_birthday;
    }

    public String getUser_introduce() {
        return user_introduce;
    }

    public void setUser_introduce(String user_introduce) {
        this.user_introduce = user_introduce;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }
}
