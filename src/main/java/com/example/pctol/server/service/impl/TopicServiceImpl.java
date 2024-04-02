package com.example.pctol.server.service.impl;

import com.example.pctol.common.constant.ExcelConstant;
import com.example.pctol.common.constant.MsgConstant;
import com.example.pctol.common.constant.TopicConstant;
import com.example.pctol.common.properties.BaseContext;
import com.example.pctol.common.utils.ExcelOp;
import com.example.pctol.pojo.entity.*;
import com.example.pctol.server.mapper.*;
import com.example.pctol.server.service.TopicExcelService;
import com.example.pctol.server.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hp
 * @date 2024/3/29
 */
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private RadioesMapper radioesMapper;
    @Autowired
    private MulChoMapper mulChoMapper;
    @Autowired
    private JudgMapper judgMapper;
    @Autowired
    private FitbMapper fitbMapper;
    @Autowired
    private VocaMapper vocaMapper;
    @Autowired
    private TopicExcelService topicExcelService;

    @Override
    public void dleSubject(Integer id, LocalDateTime localDateTime,Integer newId) {
        radioesMapper.updateSubject(id,localDateTime,newId);
        mulChoMapper.updateSubject(id,localDateTime,newId);
        judgMapper.updateSubject(id,localDateTime,newId);
        fitbMapper.updateSubject(id,localDateTime,newId);
        vocaMapper.updateSubject(id,localDateTime,newId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void excelHandler(MultipartFile file, Integer type) throws Exception {
        //保存到本地
        String path=new ExcelOp().save(file,type,null);
        //记录此次存储
        TopicExcel topicExcel=new TopicExcel(null,file.getOriginalFilename(),path, BaseContext.getLoginInfo(),LocalDateTime.now(),null);
        topicExcelService.insert(topicExcel);
        //读取excel
        topicExcelService.readExcel(path,type);
        //更新数据库，写入错误行开始(如果有)
    }

    //excel存入数据库
    @Override
    public <T> void save(List<T> cachedDataList, int type) throws Exception {
        if(type==TopicConstant.RADIOES){
            System.out.print("单选excel:");
            List list=Radioes.getList(cachedDataList);
            System.out.println(list.size());
            System.out.println(list);
        } else if (type==TopicConstant.MULTIPLE_CHOICES) {
            System.out.print("多选excel:");
            List list= MultipleChoices.getList(cachedDataList);
            System.out.println(list.size());
            System.out.println(list);
        }else if(type==TopicConstant.JUDGMENT){
            System.out.print("判断excel:");
            List list= Judgment.getList(cachedDataList);
            System.out.println(list.size());
            System.out.println(list);
        } else if (type==TopicConstant.FILL_IN_THE_BLANK) {
            System.out.print("填空excel:");
            List list= FillInTheBlank.getList(cachedDataList);
            System.out.println(list.size());
            System.out.println(list);
        } else if (type==TopicConstant.VOCABULARY_QST) {
            System.out.print("应用excel:");
            List list=VocabularyQst.getList(cachedDataList);
            System.out.println(list.size());
            System.out.println(list);
        }else {
            throw new Exception(ExcelConstant.FAILED_TYPE);
        }
    }
}
