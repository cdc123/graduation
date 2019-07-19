package com.graduation.service.Impl;

import com.alibaba.fastjson.JSON;
import com.graduation.dao.TestDao;
import com.graduation.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao dao;

    /**
     * 测试
     * @param str
     * @return String
     */
    @Override
    public String test(String str) {
        List<Map<String, Object>> list = dao.test11(str);
        for (Map<String, Object> map : list) {
            System.out.println(JSON.toJSONString(map));
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        }
        String str1 = "";
        if (list.size() > 0) {
            str1 = "success";
        } else {
            str1 = "error";
        }
        return str1;
    }

    /**
     * 测试获取用户session
     * @param str
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> usertest(String str){
        return dao.test22(str);
    }
}
