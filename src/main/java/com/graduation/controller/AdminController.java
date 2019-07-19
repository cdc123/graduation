package com.graduation.controller;

import com.graduation.service.AdminService;
import com.graduation.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(value = {"/getUser"})
    public List getUser(int pagenumber) {
        return adminService.getUser(pagenumber);
    }

    @PostMapping(value = {"/deleteUser"})
    public void deleteUser(int user_id) {
        adminService.deleteUser(user_id);
    }

    @PostMapping(value = {"/getVideo"})
    public List getVideo() {
        return adminService.getVideo();
    }

    @PostMapping(value = {"/deleteVideo"})
    public void deleteVideo(int video_id) {
        adminService.deleteVideo(video_id);
    }

    @PostMapping(value = {"/getMessage"})
    public List getMessage() {
        return adminService.getMessage();
    }

    @PostMapping(value = {"/deleteMessage"})
    public void deleteMessage(int msg_id) {
        adminService.deleteMessage(msg_id);
    }
    @PostMapping(value = {"/getUpVideo"})
    public List getUpVideo() {
        return adminService.getUpVideo();
    }

    @PostMapping(value = {"/deleteUpVideo"})
    public void deleteUpVideo(int upv_id) {
        adminService.deleteUpVideo(upv_id);
    }
}
