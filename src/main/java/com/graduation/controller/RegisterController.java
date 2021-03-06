package com.graduation.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.service.RegisterLoginService;

@RestController
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	RegisterLoginService service;

	/* 判断手机号是否可注册 */
	@PostMapping(value = "/checkUserPhone")
	public boolean checkUserPhone(HttpServletRequest request, HttpServletResponse response) {
		boolean flag = false;
		try {
			flag = service.getUserByPhone(request.getParameter("userPhone"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;/* true:可以注册;false:注册手机号已存在 */
	}

	/* 注册 */
	@PostMapping(value = "/register")
	public boolean register(HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> list = null;
		boolean flag = false;
		try {
			list = service.register(request.getParameter("userPhone"), request.getParameter("password"));
			if (list != null && !"".equals(list)) {
				flag = true;
				request.getSession().setAttribute("sessionListForUser", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;/* true:注册成功;false:注册失败 */
	}

}
