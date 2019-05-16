package com.graduation.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graduation.dao.FilmLibraryDao;
import com.graduation.service.FilmLibraryService;

@Service
public class FilmLibraryServiceImpl implements FilmLibraryService {

	@Autowired
	FilmLibraryDao dao;

	public List<Map<String, Object>> getVideoSort() {
		List<Map<String, Object>> list = null;
		try {
			list = dao.getVideoSort();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getVideoRegion() {
		List<Map<String, Object>> list = null;
		try {
			list = dao.getVideoRegion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getVideoType() {
		List<Map<String, Object>> list = null;
		try {
			list = dao.getVideoType();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getLimitVideoBySRTO(String sort, String region, String type, int order,
			int start) {
		List<Map<String, Object>> list = null;
		try {
			if ("全部".equals(sort)) {
				sort = null;
			} else {
				sort = "'" + sort + "'";
			}
			if ("全部".equals(region)) {
				region = null;
			} else {
				region = "'" + region + "'";
			}
			if ("全部".equals(type)) {
				type = null;
			} else {
				type = "'" + type + "'";
			}
			list = dao.getLimitVideoBySRTO(sort, region, type, order, start, (start + 18));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getLimitUpVideoBySRTO(int order, int start) {
		List<Map<String, Object>> list = null;
		try {
			list = dao.getLimitUpVideoBySRTO(order, start, (start + 18));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
