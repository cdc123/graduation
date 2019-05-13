package com.graduation.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.service.HomeService;

import net.sf.json.JSON;
import net.sf.json.JSONArray;

@RestController
@RequestMapping("/home")
public class HomeController {

	@Autowired
	HomeService service;

	/* 查询当前登录用户信息 */
	@PostMapping(value = "/getUserBySession")
	public List<Map<String, Object>> getUserBySession(HttpServletRequest request) {
		List<Map<String, Object>> list = null;
		try {
			list = (List<Map<String, Object>>) request.getSession().getAttribute("sessionListForUser");
			System.out.println("当前登录用户 : " + list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/* 根据搜索框输入关键字进行模糊查询 */
	@PostMapping(value = "/getVideoByFirstName")
	public List<String> getVideoByFirstName(HttpServletRequest request) {
		List<String> list = null;
		try {
			list = service.getVideoByFirstName(request.getParameter("firstName"));
			System.out.println("模糊查询视频 : " + list);
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
		String videoName = null;
		String relVideoName = null;
		try {
			videoName = request.getParameter("videoName");
			request.getSession().setAttribute("searchKeyword", videoName);
			list = service.getVideoByName(videoName);
			json = JSONArray.fromObject(list);
			relVideoName = String.valueOf(((Map) (json.get(0))).get("video_name").toString());
			if (list.size() == 1 && relVideoName.equals(videoName)) {
				request.getSession().setAttribute("videosession",
						String.valueOf(((Map) (json.get(0))).get("video_id").toString()));
			}
			request.getSession().setAttribute("searchVideoList", list);
			System.out.println("混合查找视频 : " + list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/* 登出 */
	@PostMapping(value = "/logOut")
	public void logOut(HttpServletRequest request) {
		try {
			request.getSession().setAttribute("sessionListForUser", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 查询历史纪录 */
	@PostMapping(value = "/getHistoryByUserId")
	public List<Map<String, Object>> getHistoryByUserId(HttpServletRequest request) {
		List<Map<String, Object>> list = null;
		try {
			list = service.getHistoryByUserId(request.getParameter("userId"));
			System.out.println("播放记录 : " + list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/* 根据视频ID查询视频名称 */
	@PostMapping(value = "/getVideoById")
	public String getVideoById(HttpServletRequest request) {
		String videoName = null;
		try {
			videoName = String.valueOf(
					((Map) (JSONArray.fromObject(service.getVideoById(request.getParameter("videoId")))).get(0))
							.get("video_name").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return videoName;
	}

	/* 根据视频名称查询视频 */
	@PostMapping(value = "/getVideoByNameForHistory")
	public void getVideoByNameForHistory(HttpServletRequest request) {
		List<Map<String, Object>> list = null;
		try {
			list = service.getVideoByName(request.getParameter("videoName"));
			if (list.size() > 0) {
				request.getSession().setAttribute("videosession",
						Integer.valueOf(((Map) (JSONArray.fromObject(list)).get(0)).get("video_id").toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 根据上传视频ID查询视频名称 */
	@PostMapping(value = "/getUpVideoById")
	public String getUpVideoById(HttpServletRequest request) {
		int upVideoId = 0;
		String upVideoName = null;
		try {
			upVideoId = Integer.valueOf(request.getParameter("upVideoId"));
			upVideoId = -upVideoId;
			upVideoName = String
					.valueOf(((Map) (JSONArray.fromObject(service.getUpVideoById(String.valueOf(upVideoId)))).get(0))
							.get("upv_name").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return upVideoName;
	}

	/* 根据上传视频名称查询视频 */
	@PostMapping(value = "/getUpVideoByName")
	public void getUpVideoByName(HttpServletRequest request) {
		List<Map<String, Object>> list = null;
		try {
			list = service.getUpVideoByName(request.getParameter("videoName"));
			if (list.size() > 0) {
				int upvId = Integer.valueOf(((Map) (JSONArray.fromObject(list)).get(0)).get("upv_id").toString());
				upvId = -upvId;
				request.getSession().setAttribute("videosession", upvId);
				System.out.println("videosession : " + upvId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 根据视频分类查询视频 */
	@PostMapping(value = "/getVideoBySort")
	public List<Map<String, Object>> getVideoBySort(HttpServletRequest request) {
		List<Map<String, Object>> list = null;
		try {
			list = service.getVideoBySort(request.getParameter("videoSort"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/* 查询上传视频 */
	@PostMapping(value = "/getUploadVideo")
	public List<Map<String, Object>> getUploadVideo(HttpServletRequest request) {
		List<Map<String, Object>> list = null;
		try {
			list = service.getUploadVideo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/* 取消收藏视频 */
	@PostMapping(value = "/cancelCollection")
	public String cancelCollection(HttpServletRequest request) {
		try {
			service.cancelCollection(Integer.valueOf(
					((Map) ((JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"))).get(0)))
							.get("user_id").toString()),
					Integer.valueOf(request.getParameter("videoId")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "1";
	}

	/* 删除上传视频 */
	@PostMapping(value = "/deleteUploadVideo")
	public String deleteUploadVideo(HttpServletRequest request) {
		try {
			service.deleteUploadVideo(Integer.valueOf(
					((Map) ((JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"))).get(0)))
							.get("user_id").toString()),
					Integer.valueOf(request.getParameter("upvId")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "1";
	}

	@PostMapping(value = "/playUpVideo")
	public String playUpVideo(HttpServletRequest request) {
		int upvId = 0;
		try {
			upvId = Integer.valueOf(request.getParameter("upvId"));
			upvId = -upvId;
			System.out.println("upvId : " + upvId);
			request.getSession().setAttribute("videosession", upvId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "1";
	}
}
