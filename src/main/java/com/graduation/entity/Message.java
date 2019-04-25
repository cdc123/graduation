package com.graduation.entity;

import java.util.Date;

public class Message {

    private int msg_id;
    private int video_id;
    private int user_id;
    private Date msg_date;
    private String msg_text;
    private int msg_praise;

    public int getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(int msg_id) {
        this.msg_id = msg_id;
    }

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getMsg_date() {
        return msg_date;
    }

    public void setMsg_date(Date msg_date) {
        this.msg_date = msg_date;
    }

    public String getMsg_text() {
        return msg_text;
    }

    public void setMsg_text(String msg_text) {
        this.msg_text = msg_text;
    }

    public int getMsg_praise() {
        return msg_praise;
    }

    public void setMsg_praise(int msg_praise) {
        this.msg_praise = msg_praise;
    }
    @Override
    public String toString() {
        return "message{" +
                "user_id=" + user_id +
                ", msg_text='" + msg_text + '\'' +
                ", msg_date='" + msg_date + '\'' +
                '}';
    }
}