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

    @Override
    public List getVideo(String video_id) {
       try {
           return dao.getVideo(Integer.parseInt(video_id));
       }catch (Exception e){
           System.out.println(e.getMessage());
           return null;
       }
    }

    public List recommend(String video_id){
        return dao.recommend(Integer.parseInt(video_id));
    }
}
