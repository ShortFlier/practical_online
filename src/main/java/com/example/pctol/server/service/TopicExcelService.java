package com.example.pctol.server.service;

import com.example.pctol.pojo.entity.TopicExcel;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author hp
 * @date 2024/4/1
 */
public interface TopicExcelService {
    void insert(TopicExcel topicExcel);

    void readExcel(MultipartFile file, Integer type);
}
