package com.graduation.controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public List<Map<String, Object>> videoUpload(MultipartFile file, String setVideoName, HttpServletRequest request)
			throws Exception {
		/* 视频名 */
		String upvName = null;
		JSONArray json = null;
		/* 用户Id */
		String userId = null;
		SimpleDateFormat sdf = null;
		SimpleDateFormat sdf2 = null;
		/* 上传日期 */
		String upvDate = null;
		String realPath1 = null;
		String realPath2 = null;
		String fileName = null;
		int extIndex = 0;
		String ext = null;
		String upvVideo = null;
		String upvImage = null;
		List<Map<String, Object>> list = null;
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
			realPath1 = request.getServletContext().getRealPath("/video/uploadVideos/");
			fileName = file.getOriginalFilename();
			extIndex = fileName.lastIndexOf(".");
			ext = fileName.substring(extIndex, fileName.length());
			/* 视频存放路径 */
			upvVideo = realPath1 + upvName + ext;
			/* 上传文件 */
			file.transferTo(new File(upvVideo));
			upvVideo = "/video/uploadVideos" + upvName + ext;
			realPath2 = request.getServletContext().getRealPath("/home/jar/upv_image/");
			fetchFrame(realPath1 + upvName + ext, realPath2 + upvName + ".jpg");
			upvImage = "/home/jar/upv_image/" + upvName + ".jpg";
			/* 插入操作并返回插入数据 */
			list = service.uploadVideo(upvName, userId, upvDate, upvVideo, upvImage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取指定视频的帧并保存为图片至指定目录
	 * 
	 * @param videofile 源视频文件路径
	 * @param framefile 截取帧的图片存放路径
	 * @throws Exception
	 */
	public static void fetchFrame(String videofile, String framefile) throws Exception {
		long start = System.currentTimeMillis();
		File targetFile = new File(framefile);
		FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videofile);
		ff.start();
		int lenght = ff.getLengthInFrames();
		int i = 0;
		Frame f = null;
		while (i < lenght) {
			// 过滤前5帧，避免出现全黑的图片，依自己情况而定
			f = ff.grabFrame();
			if ((i > 5) && (f.image != null)) {
				break;
			}
			i++;
		}
		IplImage img = f.image;
		int owidth = img.width();
		int oheight = img.height();
		// 对截取的帧进行等比例缩放
		int width = 800;
		int height = (int) (((double) width / owidth) * oheight);
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		bi.getGraphics().drawImage(f.image.getBufferedImage().getScaledInstance(width, height, Image.SCALE_SMOOTH), 0,
				0, null);
		ImageIO.write(bi, "jpg", targetFile);
		// ff.flush();
		ff.stop();
	}

}
