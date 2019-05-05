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
	public List<Map<String, Object>> getUserBySession(HttpServletRequest request, HttpServletResponse response) {
		JSONArray json = null;
		List<Map<String, Object>> list = null;
		Map<String, Object> map = null;
		Map<String, Object> map2 = null;
		try {
			list = new ArrayList<Map<String, Object>>();
			map = new HashMap<String, Object>();
			map2 = new HashMap<String, Object>();
			/* 提示信息 */
			System.out.println("loginUser : " + request.getSession().getAttribute("sessionListForUser"));
			if (request.getSession().getAttribute("sessionListForUser") != null
					&& !"".equals(request.getSession().getAttribute("sessionListForUser"))) {
				json = JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"));
				if (((Map) (json.get(0))).get("user_id") != null && !"".equals(((Map) (json.get(0))).get("user_id"))) {
					map2.put("userId", Integer.valueOf(((Map) (json.get(0))).get("user_id").toString()));
				} else {
					map2.put("userId", "");
				}
				if (((Map) (json.get(0))).get("user_password") != null
						&& !"".equals(((Map) (json.get(0))).get("user_password"))) {
					map2.put("userPassword", String.valueOf(((Map) (json.get(0))).get("user_password").toString()));
				} else {
					map2.put("userPassword", "");
				}
				if (((Map) (json.get(0))).get("user_phone") != null
						&& !"".equals(((Map) (json.get(0))).get("user_phone"))) {
					map2.put("userPhone", String.valueOf(((Map) (json.get(0))).get("user_phone").toString()));
				} else {
					map2.put("userPhone", "");
				}
				if (((Map) (json.get(0))).get("user_power") != null
						&& !"".equals(((Map) (json.get(0))).get("user_power"))) {
					map2.put("userPower", String.valueOf(((Map) (json.get(0))).get("user_power").toString()));
				} else {
					map2.put("userPower", "");
				}
				if (((Map) (json.get(0))).get("user_name") != null
						&& !"".equals(((Map) (json.get(0))).get("user_name"))) {
					map2.put("userName", String.valueOf(((Map) (json.get(0))).get("user_name").toString()));
				} else {
					map2.put("userName", "");
				}
				if (((Map) (json.get(0))).get("user_sex") != null
						&& !"".equals(((Map) (json.get(0))).get("user_sex"))) {
					map2.put("userSex", String.valueOf(((Map) (json.get(0))).get("user_sex").toString()));
				} else {
					map2.put("userSex", "");
				}
				if (((Map) (json.get(0))).get("user_address") != null
						&& !"".equals(((Map) (json.get(0))).get("user_address"))) {
					map2.put("userAddress", String.valueOf(((Map) (json.get(0))).get("user_address").toString()));
				} else {
					map2.put("userAddress", "");
				}
				if (((Map) (json.get(0))).get("user_birthday") != null
						&& !"".equals(((Map) (json.get(0))).get("user_birthday"))) {
					map2.put("userBirthday", Date.valueOf(((Map) (json.get(0))).get("user_birthday").toString()));
				} else {
					map2.put("userBirthday", "");
				}
				if (((Map) (json.get(0))).get("user_introduce") != null
						&& !"".equals(((Map) (json.get(0))).get("user_introduce"))) {
					map2.put("userIntroduce", String.valueOf(((Map) (json.get(0))).get("user_introduce").toString()));
				} else {
					map2.put("userIntroduce", "");
				}
				if (((Map) (json.get(0))).get("user_image") != null
						&& !"".equals(((Map) (json.get(0))).get("user_image"))) {
					map2.put("userImage", String.valueOf(((Map) (json.get(0))).get("user_image").toString()));
				} else {
					map2.put("userImage", "");
				}
				list.add(map2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/* 根据搜索框输入关键字进行模糊查询 */
	@PostMapping(value = "/getVideoByFirstName")
	public List<String> getVideoByFirstName(HttpServletRequest request, HttpServletResponse response) {
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
	public List<Map<String, Object>> getVideoByName(HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> list = null;
		String videoName = request.getParameter("videoName");
		try {
			list = new ArrayList<Map<String, Object>>();
			list = service.getVideoByName(videoName);
			request.getSession().setAttribute("searchVideoList", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/* 登出 */
	@PostMapping(value = "/logOut")
	public void logOut(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute("sessionListForUser", "");
	}

	/* 查询历史纪录 */
	@PostMapping(value = "/getHistoryByUserId")
	public List<Map<String, Object>> getHistoryByUserId(HttpServletRequest request, HttpServletResponse response) {
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
	public String getVideoById(HttpServletRequest request, HttpServletResponse response) {
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
	public void getVideoByNameForHistory(HttpServletRequest request, HttpServletResponse response) {
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
}
