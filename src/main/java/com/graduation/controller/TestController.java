package com.graduation.controller;

import com.graduation.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
    @Autowired
    private TestService service;

    @RequestMapping(value = {"/test"}, method = RequestMethod.GET)
    public Object addressQuery() {
        String str = "001";
        return service.test(str);
    }
}
