package com.graduation.controller;

import com.graduation.service.MessageService;
import com.graduation.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService service;

    @PostMapping(value = {"/addMessage"})
    public void addMessage(HttpServletRequest request, String msg_text, int video_id) {
        int user_id = Integer.valueOf(SessionUtils.get_session_user(request));
        service.addMessage(user_id, video_id, msg_text);
    }

    @PostMapping(value = {"/getMessage"})
    public List getMessage(int pagenumber, int video_id) {
        return service.gerMessage(pagenumber, video_id);
    }
}
