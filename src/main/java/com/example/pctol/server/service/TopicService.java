package com.example.pctol.server.service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author hp
 * @date 2024/3/29
 */
public interface TopicService {

    void dleSubject(Integer id, LocalDateTime localDateTime,Integer newId);
}
