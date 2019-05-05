package com.graduation.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MessageDao {
    /**
     * 保存评价
     * @param user_id,video_id,msg_text
     * @return
     */
    void addMessage(@Param("user_id")int user_id,@Param("video_id")int video_id,@Param("msg_text")String msg_text);

    /**
     * 获取评价
     * @param num1,num2,video_id
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getMessage(@Param("num1")int num1,@Param("num2")int num2,@Param("video_id")int video_id);

    /**
     * 获取该视频评价数量
     * @param video_id
     * @return int
     */
    int getCommentCount(@Param("video_id")int video_id);
}
