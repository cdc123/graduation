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

	/* 视频上传 */
	@RequestMapping("/videoUpload")
	public String videoUpload(MultipartFile file, String setVideoName, HttpServletRequest request) throws Exception {
		String flag = "0";
		/* 视频名 */
		String upvName = null;
		JSONArray json = null;
		/* 用户Id */
		String userId = null;
		SimpleDateFormat sdf = null;
		SimpleDateFormat sdf2 = null;
		/* 上传日期 */
		String upvDate = null;
		String realPath = null;
		String fileName = null;
		int extIndex = 0;
		String ext = null;
		String upvVideo = null;
		try {
			upvName = setVideoName;
			Date date = new Date();
			if (upvName == null || "".equals(upvName)) {
				sdf = new SimpleDateFormat("yyyyMMddhhmmss");
				String defultVideoName = sdf.format(date);
				upvName = defultVideoName;
			}
			json = JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"));
			userId = String.valueOf(((Map) (json.get(0))).get("user_id").toString());
			sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			upvDate = sdf2.format(date);
			realPath = request.getServletContext().getRealPath("/video/uploadVideos/");
			fileName = file.getOriginalFilename();
			extIndex = fileName.lastIndexOf(".");
			ext = fileName.substring(extIndex, fileName.length());
			/* 视频存放路径 */
			upvVideo = realPath + upvName + ext;
			/* 上传文件 */
			file.transferTo(new File(upvVideo));
			upvVideo = "../video/uploadVideos/" + upvName + ext;
			/* 插入操作并返回插入数据 */
			List<Map<String, Object>> list = service.uploadVideo(upvName, userId, upvDate, upvVideo);
			if (list.size() > 0) {
				flag = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
