package com.graduation.controller;

import com.graduation.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
