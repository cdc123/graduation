package com.graduation.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.service.RegisterLoginService;
import com.graduation.utils.JsonDateValueProcessor;
import com.graduation.utils.SessionUtils;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	RegisterLoginService service;

	/* 检查手机号是否注册过 */
	@PostMapping(value = "/checkUserPhone")
	public boolean checkUserPhone(HttpServletRequest request, HttpServletResponse response) {
		String userPhone = request.getParameter("userPhone");
		boolean flag = false;
		try {
			flag = service.getUserByPhone(userPhone);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return !flag;/* true:手机号可登录;false:手机号还未注册 */
	}

	/* 用户登录 */
	@PostMapping(value = "/login")
	public List<Map<String, Object>> login(HttpServletRequest request, HttpServletResponse response) {
		String userPhone = request.getParameter("userPhone");
		String password = request.getParameter("password");
		List<Map<String, Object>> list = null;
		JSONArray json = null;
		try {
			list = new ArrayList<Map<String, Object>>();
			list = service.login(userPhone, password);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
			json = JSONArray.fromObject(list, jsonConfig);
			String relPassword = String.valueOf(((Map) json.get(0)).get("user_password").toString());
			if (relPassword.equals(password)) {
				request.getSession().setAttribute("sessionListForUser", list);
			} else {
				list = null;
			}
		} catch (Exception e) {
		}
		return list;
	}

}
