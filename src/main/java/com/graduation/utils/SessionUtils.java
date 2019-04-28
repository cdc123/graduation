package com.graduation.utils;

import net.sf.json.JSONArray;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: SessionProcess </p>
 *
 * <p>Description: Session处理类 </p>
 *
 * @author chendacheng
 * @version 1.0.0
 */

public class SessionUtils {
    public static void set_session_int(HttpServletRequest request, String session, int i) {
        request.getSession().setAttribute(session, i);
    }

    public static void set_session_String(HttpServletRequest request, String session, String str) {
        request.getSession().setAttribute(session, str);
    }

    public static void set_session_List(HttpServletRequest request, String session, List<Map<String, Object>> list) {
        request.getSession().setAttribute(session, list);
    }

    public static String get_session_String(HttpServletRequest request, String session) {
        return String.valueOf(request.getSession().getAttribute(session));
    }

    public static List<Map<String, Object>> get_session_List(HttpServletRequest request, String session) {
        JSONArray json = JSONArray.fromObject(request.getSession().getAttribute(session));
        return json;
    }

    public static Map<String, Object> get_session_Map(HttpServletRequest request, String session) {
        JSONArray json = JSONArray.fromObject(request.getSession().getAttribute(session));
        return (Map) json.get(0);
    }

    public static void set_session_video(HttpServletRequest request, String str) {
        request.getSession().setAttribute("videosession", str);
    }

    public static String get_session_video(HttpServletRequest request) {
        return String.valueOf(request.getSession().getAttribute("videosession"));
    }

    public static String get_session_user(HttpServletRequest request) {
        JSONArray json = JSONArray.fromObject(request.getSession().getAttribute("sessionListForUser"));
        return ((Map) json.get(0)).get("user_id").toString();
    }
}
