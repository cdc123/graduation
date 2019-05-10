package com.graduation.controller;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.service.PersonalInfoService;

import net.sf.json.JSONArray;
import sun.misc.BASE64Decoder;

@RestController
@RequestMapping("/infoChange")
public class PersonalInfoController {

	@Autowired
	PersonalInfoService service;

	/* 更新昵称 */
	@PostMapping(value = "/updateUserName")
	public String updateUserName(HttpServletRequest request, HttpServletResponse response) {
		JSONArray json = null;
		List<Map<String, Object>> list = null;
		String userName = request.getParameter("userName");
		String flag = "0";
		try {
			json = new JSONArray();
			list = new ArrayList<Map<String, Object>>();
			json = JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"));
			String userPhone = String.valueOf(((Map) (json.get(0))).get("user_phone").toString());
			String relUserName = String.valueOf(((Map) (json.get(0))).get("user_name").toString());
			if (!userName.equals(relUserName)) {
				list = service.updateUserName(userPhone, userName);
				json = JSONArray.fromObject(list);
				relUserName = String.valueOf(((Map) (json.get(0))).get("user_name").toString());
				if (userName.equals(relUserName)) {
					flag = "1";
					request.getSession().setAttribute("sessionListForUser", list);
				}
			} else {
				return "2";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;/* 1：成功；0：失败；2：无变化 */
	}

	/* 更新性别 */
	@PostMapping(value = "/updateUserSex")
	public String updateUserSex(HttpServletRequest request, HttpServletResponse response) {
		JSONArray json = null;
		List<Map<String, Object>> list = null;
		String userSex = request.getParameter("userSex");
		String flag = "0";
		try {
			json = new JSONArray();
			list = new ArrayList<Map<String, Object>>();
			json = JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"));
			String userPhone = String.valueOf(((Map) (json.get(0))).get("user_phone").toString());
			String relUserSex = String.valueOf(((Map) (json.get(0))).get("user_sex").toString());
			if (!userSex.equals(relUserSex)) {
				list = service.updateUserSex(userPhone, userSex);
				json = JSONArray.fromObject(list);
				relUserSex = String.valueOf(((Map) (json.get(0))).get("user_sex").toString());
				if (userSex.equals(relUserSex)) {
					flag = "1";
					request.getSession().setAttribute("sessionListForUser", list);
				}
			} else {
				return "2";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;/* 1：成功；0：失败；2：无变化 */
	}

	/* 更新地址 */
	@PostMapping(value = "/updateUserAddress")
	public String updateUserAddress(HttpServletRequest request, HttpServletResponse response) {
		JSONArray json = null;
		List<Map<String, Object>> list = null;
		String userAddress = request.getParameter("userAddress");
		String flag = "0";
		try {
			json = new JSONArray();
			list = new ArrayList<Map<String, Object>>();
			json = JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"));
			String userPhone = String.valueOf(((Map) (json.get(0))).get("user_phone").toString());
			String relUserAddress = String.valueOf(((Map) (json.get(0))).get("user_address").toString());
			if (!userAddress.equals(relUserAddress)) {
				list = service.updateUserAddress(userPhone, userAddress);
				json = JSONArray.fromObject(list);
				relUserAddress = String.valueOf(((Map) (json.get(0))).get("user_address").toString());
				if (userAddress.equals(relUserAddress)) {
					flag = "1";
					request.getSession().setAttribute("sessionListForUser", list);
				}
			} else {
				return "2";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;/* 1：成功；0：失败；2：无变化 */
	}

	/* 设置生日 */
	@PostMapping(value = "/updateUserBirthday")
	public String updateUserBirthday(HttpServletRequest request, HttpServletResponse response) {
		JSONArray json = null;
		List<Map<String, Object>> list = null;
		String userBirthday = request.getParameter("userBirthday");
		String flag = "0";
		try {
			json = new JSONArray();
			list = new ArrayList<Map<String, Object>>();
			if (userBirthday != null && !"".equals(userBirthday)) {
				json = JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"));
				String userPhone = String.valueOf(((Map) (json.get(0))).get("user_phone").toString());
				Map<String, Object> m = (Map<String, Object>) ((Map) (json.get(0))).get("user_birthday");
				String year = (int) (m.get("year")) + 1900 + "";
				String month = (int) (m.get("month")) + 1 + "";
				if (month.length() == 1) {
					month = "0" + month;
				}
				String day = m.get("date") + "";
				if (day.length() == 1) {
					day = "0" + day;
				}
				String relUserBirthday = year + "-" + month + "-" + day;
				if (!userBirthday.equals(relUserBirthday)) {
					list = service.updateUserBirthday(userPhone, userBirthday);
					json = JSONArray.fromObject(list);
					relUserBirthday = String.valueOf(((Map) (json.get(0))).get("user_birthday").toString());
					m = (Map<String, Object>) ((Map) (json.get(0))).get("user_birthday");
					year = (int) (m.get("year")) + 1900 + "";
					month = (int) (m.get("month")) + 1 + "";
					if (month.length() == 1) {
						month = "0" + month;
					}
					day = m.get("date") + "";
					if (day.length() == 1) {
						day = "0" + day;
					}
					relUserBirthday = year + "-" + month + "-" + day;
					if (userBirthday.equals(relUserBirthday)) {
						flag = "1";
						request.getSession().setAttribute("sessionListForUser", list);
					}
				} else {
					return "2";
				}
			} else {
				return "0";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;/* 1：成功；0：失败；2：无变化 */
	}

	/* 更新简介 */
	@PostMapping(value = "/updateUserIntroduce")
	public String updateUserIntroduce(HttpServletRequest request, HttpServletResponse response) {
		JSONArray json = null;
		List<Map<String, Object>> list = null;
		String userIntroduce = request.getParameter("userIntroduce");
		String flag = "0";
		try {
			json = new JSONArray();
			list = new ArrayList<Map<String, Object>>();
			json = JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"));
			String userPhone = String.valueOf(((Map) (json.get(0))).get("user_phone").toString());
			String relUserIntroduce = String.valueOf(((Map) (json.get(0))).get("user_introduce").toString());
			if (!userIntroduce.equals(relUserIntroduce)) {
				list = service.updateUserIntroduce(userPhone, userIntroduce);
				json = JSONArray.fromObject(list);
				relUserIntroduce = String.valueOf(((Map) (json.get(0))).get("user_introduce").toString());
				if (userIntroduce.equals(relUserIntroduce)) {
					flag = "1";
					request.getSession().setAttribute("sessionListForUser", list);
				}
			} else {
				return "2";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;/* 1：成功；0：失败；2：无变化 */
	}

	/* 查询收藏视频 */
	@PostMapping(value = "/getFavouriteByUserId")
	public List<Map<String, Object>> getFavouriteByUserId(HttpServletRequest request, HttpServletResponse response) {
		JSONArray json = null;
		String userId = "";
		List<Map<String, Object>> list = null;
		try {
			json = JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"));
			userId = String.valueOf(((Map) (json.get(0))).get("user_id").toString());
			list = service.getFavouriteByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/* 查询上传视频 */
	@PostMapping(value = "/getUploadByUserId")
	public List<Map<String, Object>> getUploadByUserId(HttpServletRequest request, HttpServletResponse response) {
		JSONArray json = null;
		String userId = "";
		List<Map<String, Object>> list = null;
		try {
			json = JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"));
			userId = String.valueOf(((Map) (json.get(0))).get("user_id").toString());
			list = service.getUploadByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/* 上传头像 */
	@PostMapping(value = "/updateUserImage")
	public String updateUserImage(HttpServletRequest request, HttpServletResponse response) {
		String stringImage = request.getParameter("stringImage");
		Date t = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String uploadDate = df.format(t);
		String realPath = request.getServletContext().getRealPath("/image/userPhotos");
		GenerateImage(stringImage, realPath + "\\" + uploadDate + ".png");
		String userImage = "../image/userPhotos/" + uploadDate + ".png";
		JSONArray json = null;
		String userPhone = null;
		List<Map<String, Object>> list = null;
		String relUserImage = null;
		String flag = "0";
		try {
			list = new ArrayList<Map<String, Object>>();
			json = JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"));
			userPhone = String.valueOf(((Map) (json.get(0))).get("user_phone").toString());
			list = service.updateUserImage(userPhone, userImage);
			json = JSONArray.fromObject(list);
			relUserImage = String.valueOf(((Map) (json.get(0))).get("user_image").toString());
			if (userImage.equals(relUserImage)) {
				flag = "1";
				request.getSession().setAttribute("sessionListForUser", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// 对字节数组字符串进行Base64解码并生成图片
	public static boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] bytes = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(bytes);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@PostMapping(value = "/checkUpvName")
	public String checkUpvName(HttpServletRequest request, HttpServletResponse response) {
		String flag = "0";
		String videoName = request.getParameter("videoName");
		List<Map<String, Object>> list = service.checkUpvName(videoName);
		if(list.size() == 0) {
			flag = "1";
		}
		return flag;
	}

	/* 视频上传 */
	@PostMapping(value = "/uploadVideo")
	public String uploadVideo(HttpServletRequest request, HttpServletResponse response) {
		/* 获取指定视频名称 */
		String videoName = request.getParameter("videoName");
		/* 获取视频base64编码 */
		String stringVideo = request.getParameter("stringVideo");
		/* 获取上传用户信息 */
		JSONArray json = JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"));
		/* 设置视频存放路径 */
		String realPath = request.getServletContext().getRealPath("/video/uploadVideos");
		/* 默认视频名 */
		Date t = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String uploadDate = df.format(t);
		/* 设置插入数据库数据 */
		String upvName;
		String userId = String.valueOf(((Map) (json.get(0))).get("user_id").toString());
		String upvDate = df2.format(t);
		String upvVideo;
		if (videoName != null && !videoName.equals("")) {
			/* 保存视频 */
			GenerateImage(stringVideo, realPath + "\\" + videoName + ".mp4");
			upvName = videoName;
			upvVideo = realPath + "\\" + videoName + ".mp4";
		} else {
			/* 保存视频 */
			GenerateImage(stringVideo, realPath + "\\" + uploadDate + ".mp4");
			upvName = uploadDate;
			upvVideo = realPath + "\\" + uploadDate + ".mp4";
		}
		String flag = "0";
		List<Map<String, Object>> list = service.uploadVideo(upvName, userId, upvDate, upvVideo);
		if (list.size() > 0) {
			flag = "1";
		}
		return flag;
	}
}
