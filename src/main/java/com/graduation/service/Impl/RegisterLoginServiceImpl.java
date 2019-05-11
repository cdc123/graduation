package com.graduation.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graduation.dao.RegisterLoginDao;
import com.graduation.service.RegisterLoginService;

@Service
public class RegisterLoginServiceImpl implements RegisterLoginService {

	@Autowired
	RegisterLoginDao dao;

	/* 判断手机号是否可注册 */
	@Override
	public boolean getUserByPhone(String userPhone) {
		List<Map<String, Object>> list = null;
		boolean flag = false;
		try {
			list = dao.getUserByPhone(userPhone);
			if (list.size() == 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;/* true:手机号还没有注册;false:手机号已注册过 */
	}

	@Override
	public List<Map<String, Object>> register(String userPhone, String password) {
		List<Map<String, Object>> list = null;
		try {
			dao.register(userPhone, password);
			list = dao.getUserByPhone(userPhone);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> login(String userPhone, String password) {
		List<Map<String, Object>> list = null;
		try {
			list = dao.getUserByPhone(userPhone);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
