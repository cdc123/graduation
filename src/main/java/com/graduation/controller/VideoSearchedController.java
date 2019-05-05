package com.graduation.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.json.JSONArray;

@RestController
@RequestMapping("/videoSearched")
public class VideoSearchedController {

	/* 返回影片查询结果 */
	@PostMapping(value = "/getDataForVideoSearched")
	public List<Map<String, Object>> getDataForVideoSearched(HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> list = null;
		try {
			list = (List<Map<String, Object>>) request.getSession().getAttribute("searchVideoList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
