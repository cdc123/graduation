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

	/* 设置昵称 */
	@PostMapping(value = "/updateUserName")
	public String updateUserName(HttpServletRequest request, HttpServletResponse response) {
		JSONArray json = null;
		String userPhone = null;
		String relUserName = null;
		List<Map<String, Object>> list = null;
		String userName = null;
		String flag = "0";
		try {
			userName = request.getParameter("userName");
			json = JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"));
			userPhone = String.valueOf(((Map) (json.get(0))).get("user_phone").toString());
			if (((Map) (json.get(0))).get("user_name") != null && ((Map) (json.get(0))).get("user_name") != "") {
				relUserName = String.valueOf(((Map) (json.get(0))).get("user_name").toString());
			}
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

	/* 设置性别 */
	@PostMapping(value = "/updateUserSex")
	public String updateUserSex(HttpServletRequest request, HttpServletResponse response) {
		JSONArray json = null;
		String userPhone = null;
		String relUserSex = null;
		List<Map<String, Object>> list = null;
		String userSex = null;
		String flag = "0";
		try {
			userSex = request.getParameter("userSex");
			json = JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"));
			userPhone = String.valueOf(((Map) (json.get(0))).get("user_phone").toString());
			if (((Map) (json.get(0))).get("user_sex") != null && ((Map) (json.get(0))).get("user_sex") != "") {
				relUserSex = String.valueOf(((Map) (json.get(0))).get("user_sex").toString());
			}
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

	/* 设置地址 */
	@PostMapping(value = "/updateUserAddress")
	public String updateUserAddress(HttpServletRequest request, HttpServletResponse response) {
		JSONArray json = null;
		String userPhone = null;
		String relUserAddress = null;
		List<Map<String, Object>> list = null;
		String userAddress = null;
		String flag = "0";
		try {
			userAddress = request.getParameter("userAddress");
			json = JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"));
			userPhone = String.valueOf(((Map) (json.get(0))).get("user_phone").toString());
			if (((Map) (json.get(0))).get("user_address") != null && ((Map) (json.get(0))).get("user_address") != "") {
				relUserAddress = String.valueOf(((Map) (json.get(0))).get("user_address").toString());
			}
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
		String userPhone = null;
		Map<String, Object> m = null;
		String year = null;
		String month = null;
		String day = null;
		String relUserBirthday = null;
		List<Map<String, Object>> list = null;
		String userBirthday = null;
		String flag = "0";
		try {
			userBirthday = request.getParameter("userBirthday");
			if (userBirthday != null && !"".equals(userBirthday)) {
				json = JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"));
				userPhone = String.valueOf(((Map) (json.get(0))).get("user_phone").toString());
				if (((Map) (json.get(0))).get("user_birthday") != null
						&& ((Map) (json.get(0))).get("user_birthday") != "") {
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
				}
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

	/* 设置简介 */
	@PostMapping(value = "/updateUserIntroduce")
	public String updateUserIntroduce(HttpServletRequest request, HttpServletResponse response) {
		JSONArray json = null;
		String userPhone = null;
		String relUserIntroduce = null;
		List<Map<String, Object>> list = null;
		String userIntroduce = null;
		String flag = "0";
		try {
			userIntroduce = request.getParameter("userIntroduce");
			json = JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"));
			userPhone = String.valueOf(((Map) (json.get(0))).get("user_phone").toString());
			if (((Map) (json.get(0))).get("user_introduce") != null
					&& ((Map) (json.get(0))).get("user_introduce") != "") {
				relUserIntroduce = String.valueOf(((Map) (json.get(0))).get("user_introduce").toString());
			}
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
		List<Map<String, Object>> list = null;
		try {
			list = service.getFavouriteByUserId(String.valueOf(
					((Map) ((JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"))).get(0)))
							.get("user_id").toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/* 分页显示收藏视频 */
	@PostMapping(value = "/getLimitFavouriteByUserId")
	public List<Map<String, Object>> getLimitFavouriteByUserId(HttpServletRequest request,
			HttpServletResponse response) {
		List<Map<String, Object>> list = null;
		try {
			list = service.getLimitFavouriteByUserId(Integer.valueOf(
					((Map) ((JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"))).get(0)))
							.get("user_id").toString()),
					Integer.valueOf(request.getParameter("start")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/* 查询上传视频 */
	@PostMapping(value = "/getUploadByUserId")
	public List<Map<String, Object>> getUploadByUserId(HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> list = null;
		try {
			list = service.getUploadByUserId(String.valueOf(
					((Map) ((JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"))).get(0)))
							.get("user_id").toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/* 分页显示上传视频 */
	@PostMapping(value = "/getLimitUploadByUserId")
	public List<Map<String, Object>> getLimitUploadByUserId(HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> list = null;
		try {
			list = service.getLimitUploadByUserId(Integer.valueOf(
					((Map) ((JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"))).get(0)))
							.get("user_id").toString()),
					Integer.valueOf(request.getParameter("start")));
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

	/* 检查上传的视频名称是否重复使用 */
//	@PostMapping(value = "/checkUpvName")
//	public String checkUpvName(HttpServletRequest request, HttpServletResponse response) {
//		String flag = "0";
//		List<Map<String, Object>> list = null;
//		try {
//			list = service.checkUpvName(request.getParameter("videoName"));
//			if (list.size() == 0) {
//				flag = "1";
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return flag;
//	}

}
