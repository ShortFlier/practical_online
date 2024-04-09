package com.example.pctol.server.controller;

import com.example.pctol.common.constant.MsgConstant;
import com.example.pctol.common.utils.ExcelOp;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.server.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * 处理接受的excel
 *
 * @author hp
 * @date 2024/4/1
 */
@RestController
@RequestMapping("/upload")
@Slf4j
public class ExcelController {
    @Autowired
    private TopicService topicService;

    @PostMapping("/topic/excel")
    public Result topicExcel(@RequestParam("file") MultipartFile file, @RequestParam("type") Integer type)throws Exception{
        log.info("**********************************[/upload/topic/excel]************************************");
        log.info("接受excel文件：{},类型：{}",file.getOriginalFilename(),type);
        topicService.excelHandler(file,type);
        return Result.success(MsgConstant.SUCCESS);
    }

}
