package com.graduation.service;

import java.util.List;

public interface MessageService {
    void addMessage(int user_id,int video_id,String msg_text);

    List gerMessage(int pagenumber,int video_id);
}
