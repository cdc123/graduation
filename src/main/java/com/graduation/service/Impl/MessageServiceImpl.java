package com.graduation.service.Impl;

import com.graduation.dao.MessageDao;
import com.graduation.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao dao;

    /**
     * 评价
     *
     * @param user_id,video_id,msg_text
     * @return
     */
    public void addMessage(int user_id,int video_id,String msg_text){
        dao.addMessage(user_id, video_id, msg_text);
    }

    /**
     * 评价
     *
     * @param pagenumber,video_id
     * @return List
     */
    public List gerMessage(int pagenumber, int video_id){
        List<Map<String,Object>> list = dao.getMessage((pagenumber-1)*6,6,video_id);
        int count = dao.getCommentCount(video_id);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("count",count);
        list.add(map);
        return list;
    }
}
