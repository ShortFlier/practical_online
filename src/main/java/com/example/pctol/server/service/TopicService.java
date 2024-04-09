package com.example.pctol.server.service;

import com.example.pctol.pojo.DTO.TopicSearchInfoDTO;
import com.example.pctol.pojo.DTO.TopicUpdateDTO;
import com.example.pctol.pojo.VO.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author hp
 * @date 2024/3/29
 */
public interface TopicService {

    void dleSubject(Integer id, LocalDateTime localDateTime,Integer newId);

    void excelHandler(MultipartFile file, Integer type) throws Exception;

    <T> void save(List<T> cachedDataList, int type) throws Exception;

    Result statistic(int type) throws Exception;

    Result gets(TopicSearchInfoDTO topicSearchInfoDTO);

    void update(TopicUpdateDTO topicUpdateDTO);
}
