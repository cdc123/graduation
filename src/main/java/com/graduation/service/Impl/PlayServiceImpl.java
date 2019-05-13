package com.graduation.service.Impl;

import com.alibaba.fastjson.JSON;
import com.graduation.dao.PlayDao;
import com.graduation.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlayServiceImpl implements PlayService {

    @Autowired
    PlayDao dao;

    /**
     * 获取视频
     *
     * @param video_id
     * @return List
     */
    @Override
    public List getVideo(String video_id) {
        try {
            if (Integer.parseInt(video_id) > 0) {
                return dao.getVideo(Integer.parseInt(video_id));
            } else {
                int upv_id = -Integer.parseInt(video_id);
                List<Map<String, Object>> list = dao.getVideo_u(upv_id);
                String introduce = "原创视频，这里可以记录您的生活小趣味，分享游戏竞猜瞬间！";
                list.get(0).put("video_introduce", introduce);
                list.get(0).put("video_danmu", "../aiqing.html");
                return list;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * 推荐视频
     *
     * @param video_id
     * @return List
     */
    @Override
    public List recommend(String video_id) {
        try {
            int id = Integer.parseInt(video_id);
            if (id < 0) {
                id = 1;
            }
            return dao.recommend(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * 收藏
     *
     * @param video_id,user_id
     * @return int
     */
    @Override
    public int favourite(int user_id, String video_id) {
        int count = dao.f_findById(user_id, Integer.parseInt(video_id));
        if (count == 0) {
            dao.f_docreat(user_id, Integer.parseInt(video_id));
        }
        return count;
    }

    /**
     * 显示收藏
     *
     * @param video_id,user_id
     * @return int
     */
    @Override
    public int showFavourite(int user_id, String video_id) {
        int count = dao.f_findById(user_id, Integer.parseInt(video_id));
        return count;
    }

    /**
     * 获取头像
     *
     * @param user_id
     * @return List
     */
    @Override
    public List avatar(int user_id) {
        try {
            return dao.gerAvatar(user_id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * 存储观看时间
     *
     * @param user_id,video_id,cur,dur
     * @return
     */
    public void playTime(int user_id, int video_id, double cur, double dur) {
        try {
            int count = dao.h_findById(user_id, video_id);
            if (count == 0) {
                dao.setPlayTime(user_id, video_id, cur, dur);
            } else {
                dao.updatePlayTime(user_id, video_id, cur, dur);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 续播
     *
     * @param user_id,video_id
     * @return List
     */
    public List continue_v(int user_id, int video_id) {
        try {
            List<Map<String, Object>> list = dao.continue_v(user_id, video_id);
            if (null == list || list.size() == 0) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("history_holder", 0);
                list.add(map);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * 历史记录
     *
     * @param user_id
     * @return List
     */
    public List history(int user_id) {
        try {
            List<Map<String, Object>> list = dao.getHistory(user_id);
            System.out.println(JSON.toJSONString(list));
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * 历史跳转
     *
     * @param video_name
     * @return String
     */
    public String getVid(String video_name) {
        return dao.getVidByName(video_name);
    }
}
