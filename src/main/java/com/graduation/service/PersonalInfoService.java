package com.graduation.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PersonalInfoService {

	public List<Map<String, Object>> updateUserName(@Param("userPhone") String userPhone,
			@Param("userName") String userName);

	public List<Map<String, Object>> updateUserSex(@Param("userPhone") String userPhone,
			@Param("userSex") String userSex);

	public List<Map<String, Object>> updateUserAddress(@Param("userPhone") String userPhone,
			@Param("userAddress") String userAddress);

	public List<Map<String, Object>> updateUserBirthday(@Param("userPhone") String userPhone,
			@Param("userBirthday") String userBirthday);

	public List<Map<String, Object>> updateUserIntroduce(@Param("userPhone") String userPhone,
			@Param("userIntroduce") String userIntroduce);

	public List<Map<String, Object>> updateUserImage(@Param("userPhone") String userPhone,
			@Param("userImage") String userImage);

	public List<Map<String, Object>> getFavouriteByUserId(@Param("userId") String userId);

	public List<Map<String, Object>> getUploadByUserId(@Param("userId") String userId);
}
