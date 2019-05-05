package com.graduation.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RegisterLoginDao {

	public List<Map<String, Object>> getUserByPhone(@Param("userPhone") String userPhone);

	public void register(@Param("userPhone") String userPhone, @Param("password") String password);

}
