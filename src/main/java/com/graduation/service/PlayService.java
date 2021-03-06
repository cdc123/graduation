package com.graduation.service;

import java.util.List;

public interface PlayService {
    List getVideo(String video_id);

    List recommend(String video_id);

    int favourite(int user_id,String video_id);

    int showFavourite(int user_id,String video_id);

    List avatar(int user_id);

    void playTime(int user_id,int video_id,double cur,double dur);

    List continue_v(int user_id,int video_id);

    List history(int user_id);

    String getVid(String video_name);

    void updateClick(String video_id);
}
