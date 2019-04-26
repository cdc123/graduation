package com.graduation.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PlayDao {
    List<Map<String,Object>> getVideo(@Param("video_id")int video_id);

    List<Map<String,Object>> recommend(@Param("video_id")int video_id);
}
