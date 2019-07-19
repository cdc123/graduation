package com.graduation.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface FilmLibraryService {

	public List<Map<String, Object>> getVideoSort();

	public List<Map<String, Object>> getVideoRegion();

	public List<Map<String, Object>> getVideoType();

	public List<Map<String, Object>> getLimitVideoBySRTO(@Param("sort") String sort, @Param("region") String region,
			@Param("type") String type, @Param("order") int order, @Param("start") int start);

	public List<Map<String, Object>> getLimitUpVideoBySRTO(@Param("order") int order, @Param("start") int start);

}
