package com.graduation.controller;


import com.graduation.service.PlayService;
import com.graduation.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/play")
public class PlayController {

    @Autowired
    private PlayService service;

    @PostMapping(value = {"/getVideo"})
    public List getVideo(String video_id) {
        return service.getVideo(video_id);
    }

    @PostMapping(value = {"/recommend"})
    public List recommend(String video_id) {
        return service.recommend(video_id);
    }

    @PostMapping(value = {"/getSession_v"})
    public Map<String, String> getSession_v(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("video_id", SessionUtils.get_session_video(request));
        if (request.getSession().getAttribute("sessionListForUser") != null) {
            String user_id = SessionUtils.get_session_user(request);
            map.put("user_id", user_id);
        }
        return map;
    }

    @PostMapping(value = {"/setSession_v"})
    public void setSession_v(HttpServletRequest request, String video_id) {
        SessionUtils.set_session_video(request, video_id);
    }

    @PostMapping(value = {"/favourite"})
    public int favourite(HttpServletRequest request, String video_id) {
        int user_id = Integer.valueOf(SessionUtils.get_session_user(request));
        return service.favourite(user_id, video_id);
    }

    @PostMapping(value = {"/showFavourite"})
    public int showFavourite(HttpServletRequest request, String video_id) {
        int user_id = Integer.valueOf(SessionUtils.get_session_user(request));
        return service.showFavourite(user_id, video_id);
    }

    @PostMapping(value = {"/avatar"})
    public List avatar(HttpServletRequest request) {
        int user_id = Integer.valueOf(SessionUtils.get_session_user(request));
        return service.avatar(user_id);
    }

    @PostMapping(value = {"/playTime"})
    public void aplayTime(HttpServletRequest request, double history_holder, double history_total, int video_id) {
        int user_id = Integer.valueOf(SessionUtils.get_session_user(request));
        double cur = history_holder;
        double dur = history_total;
        service.playTime(user_id, video_id, cur, dur);
    }

    @PostMapping(value = {"/continue"})
    public List continue_v(HttpServletRequest request, int video_id) {
        int user_id = Integer.valueOf(SessionUtils.get_session_user(request));
        return service.continue_v(user_id, video_id);
    }

    @PostMapping(value = {"/history"})
    public List history(HttpServletRequest request) {
        int user_id = Integer.valueOf(SessionUtils.get_session_user(request));
        return service.history(user_id);
    }

    @PostMapping(value = {"/setSession_h"})
    public void setSession_h(HttpServletRequest request, String videoName) {
        String video_id = service.getVid(videoName);
        SessionUtils.set_session_video(request, video_id);
    }
}
