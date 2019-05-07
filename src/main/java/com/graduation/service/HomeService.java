package com.graduation.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface HomeService {

	public List<String> getVideoByFirstName(@Param("firstName") String firstName);

	public List<Map<String, Object>> getVideoByName(@Param("videoName") String videoName);

	public List<Map<String, Object>> getHistoryByUserId(@Param("userId") String userId);

	public List<Map<String, Object>> getVideoById(@Param("videoId") String videoId);

	public List<Map<String, Object>> getVideoBySort(@Param("videoSort") String videoSort);
	
	public List<Map<String, Object>> getUploadVideo();
}
