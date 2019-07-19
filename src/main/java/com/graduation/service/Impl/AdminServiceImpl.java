package com.graduation.service.Impl;

import com.graduation.dao.AdminDao;
import com.graduation.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    AdminDao adminDao;

    @Override
    public List getUser(int pagenumber){
        try{
        List<Map<String,Object>> list = adminDao.getUser((pagenumber-1)*6);
        Map<String,Object> map = new HashMap<String, Object>();
        list.add(map);
        return list;
//        try {
//            return adminDao.getUser();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteUser(int user_id) {
        try {
            adminDao.deleteUser(user_id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List getVideo(){
        try {
            return adminDao.getVideo();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteVideo(int video_id){
        try {
            adminDao.deleteVideo(video_id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List getMessage(){
        try {
            return adminDao.getMessage();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteMessage(int msg_id){
        try{
            adminDao.deleteMessage(msg_id);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List getUpVideo(){
        try {
            return adminDao.getUpVideo();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteUpVideo(int upv_id){
        try{
            adminDao.deleteUpVideo(upv_id);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
