package com.graduation.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminDao {

        List<Map<String, Object>> getUser(@Param("num1")int num1);

        void deleteUser(@Param("user_id")int user_id);

        List<Map<String, Object>> getVideo();

       void deleteVideo(@Param("video_id")int video_id);

        List<Map<String, Object>> getMessage();

        void deleteMessage(@Param("msg_id")int msg_id);

        List<Map<String, Object>> getUpVideo();

        void deleteUpVideo(@Param("upv_id")int upv_id);
}


