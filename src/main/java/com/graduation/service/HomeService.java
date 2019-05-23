package com.graduation.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface HomeService {

	public List<String> getVideoByFirstName(@Param("firstName") String firstName);

	public List<Map<String, Object>> getVideoByName(@Param("videoName") String videoName);

	public List<Map<String, Object>> getHistoryByUserId(@Param("userId") String userId);

	public List<Map<String, Object>> getVideoById(@Param("videoId") String videoId);

	public List<Map<String, Object>> getUpVideoById(@Param("upvId") String upvId);

	public List<Map<String, Object>> getUpVideoByName(@Param("upvName") String upvName);

	public List<Map<String, Object>> getVideoSort();

	public List<Map<String, Object>> getVideoBySort(@Param("videoSort") String videoSort);

	public List<Map<String, Object>> getUploadVideo();

	public void cancelCollection(@Param("userId") int userId, @Param("videoId") int videoId);

	public void deleteUploadVideo(@Param("userId") int userId, @Param("upvId") int upvId);
}
