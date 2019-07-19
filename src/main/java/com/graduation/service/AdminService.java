package com.graduation.service;

import java.util.List;

public interface AdminService {
    List getUser(int pagenumber);
    void deleteUser(int user_id);
    List getVideo();
    void deleteVideo(int video_id);
    List getMessage();
    void deleteMessage(int msg_id);
    List getUpVideo();
    void deleteUpVideo(int upv_id);
}
