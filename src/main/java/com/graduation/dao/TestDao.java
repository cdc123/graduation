package com.graduation.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestDao {
    /**
     * 测试
     * @param str
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> test11(@Param("str")String str);

    /**
     * 测试获取用户id
     * @param str
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> test22(@Param("str")String str);
}
