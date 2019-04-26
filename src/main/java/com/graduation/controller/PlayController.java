package com.graduation.controller;


import com.graduation.service.PlayService;
import net.sf.json.JSONArray;
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
    public Map<String, String> getSession_v(HttpServletRequest request){
        Map<String,String> map = new HashMap<String, String>();
        map.put("video_id",String.valueOf(request.getSession().getAttribute("videosession")));
        if(request.getSession().getAttribute("sessionListForUser") != null){
            JSONArray json = JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"));
            String user_id = ((Map)json.get(0)).get("user_id").toString();
            map.put("user_id",user_id);
        }
        return map;
    }

    @PostMapping(value = {"/setSession_v"})
    public void setSession_v(HttpServletRequest request,String video_id){
        request.getSession().setAttribute("videosession",video_id);
    }

    @PostMapping(value = {"/favourite"})
    public int favourite(HttpServletRequest request,String video_id){
        JSONArray json = JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"));
        int user_id = Integer.valueOf(((Map)json.get(0)).get("user_id").toString());
        return service.favourite(user_id,video_id);
    }
    @PostMapping(value = {"/showFavourite"})
    public int showFavourite(HttpServletRequest request,String video_id){
        JSONArray json = JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"));
        int user_id = Integer.valueOf(((Map)json.get(0)).get("user_id").toString());
        return service.showFavourite(user_id,video_id);
    }
}
