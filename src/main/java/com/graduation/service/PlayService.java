package com.graduation.service;

import java.util.List;

public interface PlayService {
    List getVideo(String video_id);

    List recommend(String video_id);
}
