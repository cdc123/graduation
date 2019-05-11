package com.graduation.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.graduation.service.PersonalInfoService;

import net.sf.json.JSONArray;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

	@Autowired
	PersonalInfoService service;

	@RequestMapping("/videoUpload")
	public String videoUpload(MultipartFile file, String setVideoName, HttpServletRequest request) throws Exception {
		String flag = "0";
		/* 视频名 */
		String upvName = setVideoName;
		Date date = new Date();
		if (upvName == null || "".equals(upvName)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			String defultVideoName = sdf.format(date);
			upvName = defultVideoName;
		}
		JSONArray json = JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"));
		/* 用户Id */
		String userId = String.valueOf(((Map) (json.get(0))).get("user_id").toString());
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		/* 上传日期 */
		String upvDate = sdf2.format(date);
		String realPath = request.getServletContext().getRealPath("/video/uploadVideos/");
		String fileName = file.getOriginalFilename();
		int extIndex = fileName.lastIndexOf(".");
		String ext = fileName.substring(extIndex, fileName.length());
		/* 视频存放路径 */
		String upvVideo = realPath + upvName + ext;
		/* 上传文件 */
		file.transferTo(new File(upvVideo));
		/* 插入操作并返回插入数据 */
		List<Map<String, Object>> list = service.uploadVideo(upvName, userId, upvDate, upvVideo);
		if (list.size() > 0) {
			flag = "1";
		}
		return flag;
	}
}
