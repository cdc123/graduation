package com.graduation.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.service.HomeService;
import com.graduation.utils.JsonDateValueProcessor;
import com.graduation.utils.SessionUtils;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

@RestController
@RequestMapping("/home")
public class HomeController {

	@Autowired
	HomeService service;

	/* 查询session判断登录返回用户信息 */
	@PostMapping(value = "/getUserBySession")
	public List<Map<String, Object>> getUserBySession(HttpServletRequest request) {
		List<Map<String, Object>> list = null;
		try {
			list = new ArrayList<Map<String, Object>>();
			if (request.getSession().getAttribute("sessionListForUser") != null
					&& !"".equals(request.getSession().getAttribute("sessionListForUser"))) {
				list = (List<Map<String, Object>>) request.getSession().getAttribute("sessionListForUser");
			} else {
				list = null;
			}
			/* 提示信息 */
			System.out.println("loginUser : " + request.getSession().getAttribute("sessionListForUser"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/* 根据搜索框输入关键字进行模糊查询 */
	@PostMapping(value = "/getVideoByFirstName")
	public List<String> getVideoByFirstName(HttpServletRequest request) {
		List<String> list = null;
		String firstName = request.getParameter("firstName");
		try {
			list = new ArrayList<>();
			list = service.getVideoByFirstName(firstName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/* 点击搜索按钮查询影片 */
	@PostMapping(value = "/getVideoByName")
	public List<Map<String, Object>> getVideoByName(HttpServletRequest request) {
		List<Map<String, Object>> list = null;
		JSONArray json = null;
		String videoName = request.getParameter("videoName");
		try {
			list = new ArrayList<Map<String, Object>>();
			list = service.getVideoByName(videoName);
			json = JSONArray.fromObject(list);
			String relVideoName = String.valueOf(((Map) (json.get(0))).get("video_name").toString());
			if (list.size() == 1 && relVideoName.equals(videoName)) {
				String videoId = String.valueOf(((Map) (json.get(0))).get("video_id").toString());
				request.getSession().setAttribute("videosession", videoId);
			}
			request.getSession().setAttribute("searchVideoList", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/* 登出 */
	@PostMapping(value = "/logOut")
	public void logOut(HttpServletRequest request) {
		request.getSession().setAttribute("sessionListForUser", "");
	}

	/* 查询历史纪录 */
	@PostMapping(value = "/getHistoryByUserId")
	public List<Map<String, Object>> getHistoryByUserId(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		List<Map<String, Object>> list = null;
		JSONArray json = null;
		try {
			list = new ArrayList<Map<String, Object>>();
			list = service.getHistoryByUserId(userId);
			System.out.println("/home/getHistoryByUserId list : " + list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/* 根据视频Id查询视频 */
	@PostMapping(value = "/getVideoById")
	public String getVideoById(HttpServletRequest request) {
		String videoId = request.getParameter("videoId");
		JSONArray json = null;
		String videoName = null;
		try {
			json = JSONArray.fromObject(service.getVideoById(videoId));
			videoName = String.valueOf(((Map) json.get(0)).get("video_name").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return videoName;
	}

	/* 根据视频Name查询视频 */
	@PostMapping(value = "/getVideoByNameForHistory")
	public void getVideoByNameForHistory(HttpServletRequest request) {
		String videoName = request.getParameter("videoName");
		List<Map<String, Object>> list = null;
		try {
			list = new ArrayList<Map<String, Object>>();
			list = service.getVideoByName(videoName);
			System.out.println("/homegetVideoByNameForHistory video : " + list);
			if (list.size() > 0) {
				JSONArray json = JSONArray.fromObject(list);
				int videoId = Integer.valueOf(((Map) json.get(0)).get("video_id").toString());
				System.out.println("videoId : " + videoId);
				request.getSession().setAttribute("videosession", videoId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 根据视频Name查询视频 */
	@PostMapping(value = "/getVideoBySort")
	public List<Map<String, Object>> getVideoBySort(HttpServletRequest request) {
		String videoSort = request.getParameter("videoSort");
		List<Map<String, Object>> list = null;
		try {
			list = new ArrayList<Map<String, Object>>();
			list = service.getVideoBySort(videoSort);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
