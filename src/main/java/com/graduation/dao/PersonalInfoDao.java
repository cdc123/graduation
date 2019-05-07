package com.graduation.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PersonalInfoDao {

	public void updateUserName(@Param("userPhone") String userPhone, @Param("userName") String userName);

	public void updateUserSex(@Param("userPhone") String userPhone, @Param("userSex") String userSex);

	public void updateUserAddress(@Param("userPhone") String userPhone, @Param("userAddress") String userAddress);

	public void updateUserBirthday(@Param("userPhone") String userPhone, @Param("userBirthday") String userBirthday);

	public void updateUserIntroduce(@Param("userPhone") String userPhone, @Param("userIntroduce") String userIntroduce);

	public List<Map<String, Object>> getFavouriteByUserId(@Param("userId") String userId);

	public List<Map<String, Object>> getUploadByUserId(@Param("userId") String userId);
}
