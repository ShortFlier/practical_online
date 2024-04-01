package com.example.pctol.server.service.impl;

import com.example.pctol.pojo.entity.TopicExcel;
import com.example.pctol.server.mapper.TopicExcelMapper;
import com.example.pctol.server.service.TopicExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author hp
 * @date 2024/4/1
 */
@Service
public class TopicExcelServiceImpl implements TopicExcelService {
    @Autowired
    private TopicExcelMapper topicExcelMapper;

    @Override
    public void insert(TopicExcel topicExcel) {
        topicExcelMapper.insert(topicExcel);
    }

    @Override
    public void readExcel(MultipartFile file, Integer type) {

    }


}
