package com.graduation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.service.HomeService;

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
			if (request.getSession().getAttribute("sessionListForUser") != null
					&& request.getSession().getAttribute("sessionListForUser") != "") {
				list = (List<Map<String, Object>>) request.getSession().getAttribute("sessionListForUser");
			}
			System.out.println("当前登录用户信息 : " + list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/* 搜索框start */
	/* 搜索框模糊查询 */
	@PostMapping(value = "/getVideoByFirstName")
	public List<String> getVideoByFirstName(HttpServletRequest request) {
		List<String> list = null;
		try {
			list = service.getVideoByFirstName(request.getParameter("firstName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/* 搜索框混合查询（先精确后模糊） */
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/* 搜索框end */

	/* 历史start */
	/* 查询历史纪录 */
	@PostMapping(value = "/getHistoryByUserId")
	public List<Map<String, Object>> getHistoryByUserId(HttpServletRequest request) {
		List<Map<String, Object>> list = null;
		try {
			list = service.getHistoryByUserId(request.getParameter("userId"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/* 根据视频ID查询视频名称 */
	@PostMapping(value = "/getVideoById")
	public List<Map<String, Object>> getVideoById(HttpServletRequest request) {
		List<Map<String, Object>> list = null;
		try {
			list = service.getVideoById(request.getParameter("videoId"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
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
			if (service.getUpVideoById(String.valueOf(upVideoId)) != null
					&& service.getUpVideoById(String.valueOf(upVideoId)).size() != 0) {
				upVideoName = String.valueOf(
						((Map) (JSONArray.fromObject(service.getUpVideoById(String.valueOf(upVideoId)))).get(0))
								.get("upv_name").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return upVideoName;
	}

	/* 根据上传视频名称查询视频 */
	@PostMapping(value = "/getUpVideoByName")
	public String getUpVideoByName(HttpServletRequest request) {
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
		return "1";
	}
	/* 历史end */

	/* 登出 */
	@PostMapping(value = "/logOut")
	public void logOut(HttpServletRequest request) {
		try {
			request.getSession().setAttribute("sessionListForUser", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 获取视频分类 */
	@PostMapping(value = "/getVideoSort")
	public List<Map<String, Object>> getVideoSort(HttpServletRequest request) {
		List<Map<String, Object>> list = null;
		JSONArray json = null;
		List<String> list2 = null;
		try {
			list = service.getVideoSort();
			if (list.size() > 0) {
				list2 = new ArrayList<String>();
				json = JSONArray.fromObject(list);
				for (int i = 0; i < list.size(); i++) {
					list2.add(String.valueOf(((Map) (json.get(i))).get("video_sort").toString()));
				}
				request.getSession().setAttribute("sortSessionForHome", list2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

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

	/* 根据视频分类获得各种视频列表 */
	@PostMapping(value = "/getVideoBySort2")
	public List<List<Map<String, Object>>> getVideoBySort2(HttpServletRequest request) {
		List<String> list = null;
		List<List<Map<String, Object>>> list2 = null;
		try {
			list2 = new ArrayList<List<Map<String, Object>>>();
			list = (List<String>) (request.getSession().getAttribute("sortSessionForHome"));
			for (String s : list) {
				list2.add(service.getVideoBySort(s));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list2;
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

	/* 播放上传视频 */
	@PostMapping(value = "/playUpVideo")
	public String playUpVideo(HttpServletRequest request) {
		int upvId = 0;
		try {
			upvId = Integer.valueOf(request.getParameter("upvId"));
			upvId = -upvId;
			request.getSession().setAttribute("videosession", upvId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "1";
	}

	/* 点击更多视频链接 */
	@PostMapping(value = "/getMoreVideo")
	public String getMoreVideo(HttpServletRequest request) {
		try {
			request.getSession().setAttribute("sortSession", request.getParameter("sort"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "1";
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

}
