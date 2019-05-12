package com.graduation.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HomeDao {

	public List<Map<String, String>> getVideoByFirstName(@Param("sql") String sql);

	public List<Map<String, Object>> getVideoByName(@Param("sql") String sql);

	public List<Map<String, Object>> getHistoryByUserId(@Param("userId") String userId);

	public List<Map<String, Object>> getVideoById(@Param("videoId") String videoId);

	public List<Map<String, Object>> getVideoBySort(@Param("videoSort") String videoSort);

	public List<Map<String, Object>> getUploadVideo();

	public void cancelCollection(@Param("userId") int userId, @Param("videoId") int videoId);

	public void deleteUploadVideo(@Param("userId") int userId, @Param("upvId") int upvId);
}
