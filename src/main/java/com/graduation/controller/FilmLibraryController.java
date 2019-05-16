package com.graduation.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.service.FilmLibraryService;

@RestController
@RequestMapping("/filmLibrary")
public class FilmLibraryController {

	@Autowired
	FilmLibraryService service;

	@RequestMapping("/getVideoSort")
	public List<Map<String, Object>> getVideoSort() {
		List<Map<String, Object>> list = null;
		try {
			list = service.getVideoSort();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping("/getVideoRegion")
	public List<Map<String, Object>> getVideoRegion() {
		List<Map<String, Object>> list = null;
		try {
			list = service.getVideoRegion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping("/getVideoType")
	public List<Map<String, Object>> getVideoType() {
		List<Map<String, Object>> list = null;
		try {
			list = service.getVideoType();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping("/getLimitVideoBySRTO")
	public List<Map<String, Object>> getLimitVideoBySRTO(HttpServletRequest request) {
		List<Map<String, Object>> list = null;
		try {
			if (request.getParameter("start") != null) {
				list = service.getLimitVideoBySRTO(String.valueOf(request.getParameter("sort")),
						String.valueOf(request.getParameter("region")), String.valueOf(request.getParameter("type")),
						Integer.valueOf(request.getParameter("order")), Integer.valueOf(request.getParameter("start")));
			} else {
				list = service.getLimitVideoBySRTO(String.valueOf(request.getParameter("sort")),
						String.valueOf(request.getParameter("region")), String.valueOf(request.getParameter("type")),
						Integer.valueOf(request.getParameter("order")), -1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping("/getLimitUpVideoBySRTO")
	public List<Map<String, Object>> getLimitUpVideoBySRTO(HttpServletRequest request) {
		List<Map<String, Object>> list = null;
		try {
			if (request.getParameter("start") != null) {
				list = service.getLimitUpVideoBySRTO(Integer.valueOf(request.getParameter("order")),
						Integer.valueOf(request.getParameter("start")));
			} else {
				list = service.getLimitUpVideoBySRTO(Integer.valueOf(request.getParameter("order")), -1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping("/getSortSession")
	public String getSortSession(HttpServletRequest request) {
		String sort = null;
		try {
			sort = String.valueOf(request.getSession().getAttribute("sortSession"));
			request.getSession().setAttribute("sortSession", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sort;
	}

}
