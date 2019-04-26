package com.graduation.controller;

import com.graduation.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService service;

    @RequestMapping(value = {"/test"}, method = RequestMethod.GET)
    public Object test() {
        String str = "001";
        return service.test(str);
    }

    @PostMapping(value = {"/videoSession"})
    public String videoSession(HttpServletRequest request){
        request.getSession().setAttribute("videosession",1);   //存
        return String.valueOf(request.getSession().getAttribute("videosession"));  //取
    }
}
