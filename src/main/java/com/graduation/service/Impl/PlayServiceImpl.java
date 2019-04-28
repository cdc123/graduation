package com.graduation.service.Impl;

import com.graduation.dao.PlayDao;
import com.graduation.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlayServiceImpl implements PlayService {

    @Autowired
    PlayDao dao;

    /**
     * 获取视频
     * @param video_id
     * @return List
     */
    @Override
    public List getVideo(String video_id) {
       try {
           return dao.getVideo(Integer.parseInt(video_id));
       }catch (Exception e){
           System.out.println(e.getMessage());
           return null;
       }
    }
    /**
     * 推荐视频
     * @param video_id
     * @return List
     */
    @Override
    public List recommend(String video_id){
        try {
        return dao.recommend(Integer.parseInt(video_id));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * 收藏
     * @param video_id,user_id
     * @return int
     */
    @Override
    public int favourite(int user_id, String video_id) {
        int count = dao.f_findById(user_id,Integer.parseInt(video_id));
        if(count == 0){
            dao.f_docreat(user_id,Integer.parseInt(video_id));
        }
        return count;
    }

    /**
     * 显示收藏
     * @param video_id,user_id
     * @return int
     */
    @Override
    public int showFavourite(int user_id, String video_id) {
        int count = dao.f_findById(user_id,Integer.parseInt(video_id));
        return count;
    }

    /**
     * 获取头像
     * @param user_id
     * @return List
     */
    @Override
    public List avatar(int user_id) {
        try {
            return dao.gerAvatar(user_id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * 存储观看时间
     * @param user_id,video_id,cur,dur
     * @return
     */
    public void playTime(int user_id,int video_id,double cur,double dur){
        try {
            int count = dao.h_findById(user_id,video_id);
            if(count == 0){
                dao.setPlayTime(user_id,video_id,cur,dur);
            }else {
                dao.updatePlayTime(user_id,video_id,cur,dur);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
