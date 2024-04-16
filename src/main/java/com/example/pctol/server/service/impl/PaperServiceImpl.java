package com.example.pctol.server.service.impl;

import com.example.pctol.common.constant.MsgConstant;
import com.example.pctol.common.constant.StateCode;
import com.example.pctol.common.utils.Util;
import com.example.pctol.pojo.DTO.PaperSearchDTO;
import com.example.pctol.pojo.VO.PageResult;
import com.example.pctol.pojo.VO.PaperVO;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.pojo.VO.SubVO;
import com.example.pctol.pojo.entity.Paper;

import java.util.ArrayList;
import java.util.List;
import com.example.pctol.server.mapper.PaperMapper;
import com.example.pctol.server.mapper.SubjectMapper;
import com.example.pctol.server.service.PaperService;
import com.example.pctol.server.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hp
 * @date 2024/4/15
 */
@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public Result gets(PaperSearchDTO paperSearchDTO) {
        int total=paperMapper.count(paperSearchDTO);
        if(total==0)
            return new Result(StateCode.NOT_DATA,MsgConstant.NO_DATA);
        List<Paper> paperList=paperMapper.gets(paperSearchDTO, paperSearchDTO.getStart(), paperSearchDTO.getPageSize());
        //封装成PaperVO数组返回
        List<PaperVO> paperVOList=new ArrayList<>();
        //获取SubVO数组
        List<SubVO> subVOList=subjectMapper.getList();
        for (Paper paper: paperList) {
            //根据paper.subjectId获取subjectName
            String subjectName= Util.getSubjectName(paper.getSubjectId(),subVOList);
            paperVOList.add(new PaperVO(paper,subjectName));
        }
        PageResult pageResult=new PageResult(total,paperVOList);
        return Result.success(pageResult);
    }
}
