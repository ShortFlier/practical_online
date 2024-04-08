package com.example.pctol.server.service.impl;

import com.alibaba.excel.EasyExcel;
import com.example.pctol.common.constant.TopicConstant;
import com.example.pctol.common.utils.DataListener;
import com.example.pctol.common.utils.ExcelOp;
import com.example.pctol.pojo.entity.TopicExcel;
import com.example.pctol.server.mapper.TopicExcelMapper;
import com.example.pctol.server.service.TopicExcelService;
import com.example.pctol.server.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author hp
 * @date 2024/4/1
 */
@Service
public class TopicExcelServiceImpl implements TopicExcelService {
    @Autowired
    private TopicExcelMapper topicExcelMapper;
    @Autowired
    private TopicService topicService;

    @Override
    public void insert(TopicExcel topicExcel) {
        topicExcelMapper.insert(topicExcel);
    }

    @Override
    public void readExcel(String path, Integer type) {
        ExcelOp excelOp=new ExcelOp();
        String headPath= excelOp.getSavePath();
        File file=new File(headPath,path);
        EasyExcel.read(file,new DataListener(topicService,type)).sheet().doRead();
    }

    @Override
    public void updateEor(Integer id ,String msg) {
        topicExcelMapper.updateEor(id,msg);
    }


}
