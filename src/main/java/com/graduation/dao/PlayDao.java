package com.graduation.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PlayDao {
    /**
     * 获取视频信息
     * @param video_id
     * @return List<Map<String, Object>>
     */
    List<Map<String,Object>> getVideo(@Param("video_id")int video_id);

    /**
     * 获取推荐视频信息
     * @param video_id
     * @return List<Map<String, Object>>
     */
    List<Map<String,Object>> recommend(@Param("video_id")int video_id);

    /**
     * 获取视频是否收藏
     * @param user_id,video_id
     * @return int
     */
    int f_findById(@Param("user_id")int user_id,@Param("video_id")int video_id);

    /**
     * 收藏视频
     * @param user_id,video_id
     * @return int
     */
    void f_docreat(@Param("user_id")int user_id,@Param("video_id")int video_id);

    /**
     * 获取头像
     * @param user_id
     * @return List<Map<String, Object>>
     */
    List<Map<String,Object>> gerAvatar(@Param("user_id")int user_id);

    /**
     * 获取是否有历史记录
     * @param user_id,video_id
     * @return int
     */
    int h_findById(@Param("user_id")int user_id,@Param("video_id")int video_id);

    /**
     * 存储观看时间
     * @param user_id,video_id,cur,dur
     * @return
     */
    void setPlayTime(@Param("user_id")int user_id,@Param("video_id")int video_id,@Param("cur")double cur,@Param("dur")double dur);

    /**
     * 更新观看时间
     * @param user_id,video_id,cur,dur
     * @return
     */
    void updatePlayTime(@Param("user_id")int user_id,@Param("video_id")int video_id,@Param("cur")double cur,@Param("dur")double dur);

    /**
     * 获取观看记录
     * @param user_id,video_id
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> continue_v(@Param("user_id")int user_id,@Param("video_id")int video_id);
}
