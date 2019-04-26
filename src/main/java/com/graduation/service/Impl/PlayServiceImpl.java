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
     * @return String
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
     * @return String
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
     * @return String
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
     * @return String
     */
    @Override
    public int showFavourite(int user_id, String video_id) {
        int count = dao.f_findById(user_id,Integer.parseInt(video_id));
        return count;
    }
}
