package com.graduation.entity;


import java.util.Date;

public class History {
    private int user_id;
    private int video_id;
    private Date history_time;
    private double history_holder;
    private double history_total;

    public History(){

    }
    public History(double history_holder, double history_total) {
        this.history_holder = history_holder;
        this.history_total = history_total;
    }


    public double getHistory_holder() {
        return history_holder;
    }

    public void setHistory_holder(double history_holder) {
        this.history_holder = history_holder;
    }

    public double getHistory_total() {
        return history_total;
    }

    public void setHistory_total(double history_total) {
        this.history_total = history_total;
    }



    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public Date getHistory_time() {
        return history_time;
    }

    public void setHistory_time(Date history_time) {
        this.history_time = history_time;
    }

}
