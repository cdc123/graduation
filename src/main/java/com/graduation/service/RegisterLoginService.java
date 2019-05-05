package com.graduation.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RegisterLoginService {

	public boolean getUserByPhone(@Param("userPhone") String userPhone);

	public List<Map<String, Object>> register(@Param("userPhone") String userPhone, @Param("password") String password);

	public List<Map<String, Object>> login(@Param("userPhone") String userPhone, @Param("password") String password);

}
