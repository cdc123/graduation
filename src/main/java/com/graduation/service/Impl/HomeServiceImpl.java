package com.graduation.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graduation.dao.HomeDao;
import com.graduation.service.HomeService;

import net.sf.json.JSONArray;

@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	HomeDao dao;

	/* 根据搜索框输入关键字进行模糊查询 */
	@Override
	public List<String> getVideoByFirstName(String firstName) {
		List<Map<String, String>> list = null;
		List<String> list2 = null;
		String sql = null;
		if (!firstName.contains("'")) {
			try {
				list = new ArrayList<>();
				list2 = new ArrayList<>();
				sql = "select t.video_name as 'videoName' from video t where t.video_name like ";
				sql += "'%" + firstName + "%' ";
				sql += "order by (case when t.video_name = '" + firstName + "' then 1 ";
				sql += "when t.video_name like '" + firstName + "%' then 2 ";
				sql += "when t.video_name like '%" + firstName + "%' then 3 ";
				sql += "when t.video_name like '%" + firstName + "' then 4 end) ";
				sql += "limit 10";
				list = dao.getVideoByFirstName(sql);
				for (Map<String, String> map : list) {
					list2.add(map.get("videoName"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list2;
	}

	/* 点击搜索按钮查询影片 */
	@Override
	public List<Map<String, Object>> getVideoByName(String videoName) {
		List<Map<String, Object>> list = null;
		List<Map<String, Object>> list2 = null;
		Map<String, Object> map = null;
		String sql = null;
		if (!videoName.contains("'")) {
			try {
				list = new ArrayList<Map<String, Object>>();
				list2 = new ArrayList<Map<String, Object>>();
				map = new HashMap<String, Object>();
				sql = "select * from video t where t.video_name = '" + videoName + "'";
				list = dao.getVideoByName(sql);
				if (list.size() == 0) {
					sql = "select * from video t where t.video_name like ";
					sql += "'%" + videoName + "%'";
					sql += "order by (case when t.video_name like '" + videoName + "%' then 1 ";
					sql += "when t.video_name like '%" + videoName + "%' then 2 ";
					sql += "when t.video_name like '%" + videoName + "' then 3 end) ";
					sql += "limit 10";
					list = dao.getVideoByName(sql);
				}
				/* 查询影片结果 */
				System.out.println(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/* 查询播放记录 */
	@Override
	public List<Map<String, Object>> getHistoryByUserId(String userId) {
		List<Map<String, Object>> list = null;
		try {
			list = new ArrayList<Map<String, Object>>();
			list = dao.getHistoryByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getVideoById(String videoId) {
		List<Map<String, Object>> list = null;
		try {
			list = new ArrayList<Map<String, Object>>();
			list = dao.getVideoById(videoId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
