package com.graduation.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graduation.dao.HomeDao;
import com.graduation.dao.PersonalInfoDao;
import com.graduation.dao.RegisterLoginDao;
import com.graduation.service.PersonalInfoService;

import net.sf.json.JSONArray;

@Service
public class PersonalInfoServiceImpl implements PersonalInfoService {

	@Autowired
	PersonalInfoDao dao;

	@Autowired
	RegisterLoginDao rlDao;

	@Override
	public List<Map<String, Object>> updateUserName(String userPhone, String userName) {
		List<Map<String, Object>> list = null;
		try {
			list = new ArrayList<Map<String, Object>>();
			userPhone = "'" + userPhone + "'";
			userName = "'" + userName + "'";
			dao.updateUserName(userPhone, userName);
			list = rlDao.getUserByPhone(userPhone);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateUserSex(String userPhone, String userSex) {
		List<Map<String, Object>> list = null;
		try {
			list = new ArrayList<Map<String, Object>>();
			userPhone = "'" + userPhone + "'";
			userSex = "'" + userSex + "'";
			dao.updateUserSex(userPhone, userSex);
			list = rlDao.getUserByPhone(userPhone);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateUserAddress(String userPhone, String userAddress) {
		List<Map<String, Object>> list = null;
		try {
			list = new ArrayList<Map<String, Object>>();
			userPhone = "'" + userPhone + "'";
			userAddress = "'" + userAddress + "'";
			dao.updateUserAddress(userPhone, userAddress);
			list = rlDao.getUserByPhone(userPhone);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateUserBirthday(String userPhone, String userBirthday) {
		List<Map<String, Object>> list = null;
		try {
			list = new ArrayList<Map<String, Object>>();
			userPhone = "'" + userPhone + "'";
			userBirthday = "'" + userBirthday + "'";
			dao.updateUserBirthday(userPhone, userBirthday);
			list = rlDao.getUserByPhone(userPhone);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateUserIntroduce(String userPhone, String userIntroduce) {
		List<Map<String, Object>> list = null;
		try {
			list = new ArrayList<Map<String, Object>>();
			userPhone = "'" + userPhone + "'";
			userIntroduce = "'" + userIntroduce + "'";
			dao.updateUserIntroduce(userPhone, userIntroduce);
			list = rlDao.getUserByPhone(userPhone);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateUserImage(String userPhone, String userImage) {
		List<Map<String, Object>> list = null;
		try {
			list = new ArrayList<Map<String, Object>>();
			userPhone = "'" + userPhone + "'";
			userImage = "'" + userImage + "'";
			dao.updateUserImage(userPhone, userImage);
			list = rlDao.getUserByPhone(userPhone);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getFavouriteByUserId(String userId) {
		List<Map<String, Object>> list = null;
		try {
			list = new ArrayList<Map<String, Object>>();
			list = dao.getFavouriteByUserId(userId);
		} catch (Exception e) {
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getUploadByUserId(String userId) {
		List<Map<String, Object>> list = null;
		try {
			list = new ArrayList<Map<String, Object>>();
			list = dao.getUploadByUserId(userId);
		} catch (Exception e) {
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> uploadVideo(String upvName, String userId, String upvDate, String upvVideo) {
		upvName = "'" + upvName + "'";
		userId = "'" + userId + "'";
		upvDate = "'" + upvDate + "'";
		upvVideo = upvVideo.replace("\\", "/");
		upvVideo = "'" + upvVideo + "'";
		dao.uploadVideo(upvName, userId, upvDate, upvVideo);
		List<Map<String, Object>> list = dao.getUpvVideoByName(upvName);
		return list;
	}

	@Override
	public List<Map<String, Object>> checkUpvName(String videoName) {
		videoName = "'" + videoName + "'";
		List<Map<String, Object>> list = dao.getUpvVideoByName(videoName);
		return list;
	}
}
