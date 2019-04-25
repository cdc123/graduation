package com.graduation.entity;


import java.sql.Timestamp;

public class UploadVideo {

  private int upv_id;
  private String upv_name;
  private int user_id;
  private Timestamp upv_date;
  private String upv_video;
  private String upv_state;

  public int getUpv_id() {
    return upv_id;
  }

  public void setUpv_id(int upv_id) {
    this.upv_id = upv_id;
  }

  public String getUpv_name() {
    return upv_name;
  }

  public void setUpv_name(String upv_name) {
    this.upv_name = upv_name;
  }

  public int getUser_id() {
    return user_id;
  }

  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }

  public Timestamp getUpv_date() {
    return upv_date;
  }

  public void setUpv_date(Timestamp upv_date) {
    this.upv_date = upv_date;
  }

  public String getUpv_video() {
    return upv_video;
  }

  public void setUpv_video(String upv_video) {
    this.upv_video = upv_video;
  }

  public String getUpv_state() {
    return upv_state;
  }

  public void setUpv_state(String upv_state) {
    this.upv_state = upv_state;
  }
}
