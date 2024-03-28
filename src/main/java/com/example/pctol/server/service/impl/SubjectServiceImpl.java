package com.example.pctol.server.service.impl;

import com.example.pctol.common.constant.AuditState;
import com.example.pctol.common.constant.MsgConstant;
import com.example.pctol.common.constant.StateCode;
import com.example.pctol.common.properties.BaseContext;
import com.example.pctol.pojo.DTO.SubSearchDTO;
import com.example.pctol.pojo.VO.PageResult;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.pojo.VO.SubInfoVo;
import com.example.pctol.pojo.entity.Subject;
import com.example.pctol.server.mapper.SubjectMapper;
import com.example.pctol.server.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hp
 * @date 2024/3/25
 */
@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectMapper subjectMapper;

    @Override
    public void add(Subject subject) {
        subject.setLauncher(BaseContext.getLoginInfo());
        subject.setAuditState(AuditState.AWAIT);
        subjectMapper.add(subject);
    }



    @Override
    public PageResult getData(SubSearchDTO subSearchDTO) throws Exception {
        //查询符合的数目，返回list
        int total=subjectMapper.getDataAct(subSearchDTO);
        if(total==0)
            throw new Exception(MsgConstant.NO_DATA);
        List<SubInfoVo> rows=subjectMapper.getData(subSearchDTO,subSearchDTO.getStart(),subSearchDTO.getPageSize());
        for (SubInfoVo item: rows) {
            item.computeTopicNumber();
        }
        return new PageResult(total, rows);
    }

    @Override
    public void adminAdd(Subject subject) {
        subject.setLauncher(BaseContext.getLoginInfo());
        subject.setAuditState(AuditState.ACCESS);
        subjectMapper.add(subject);
    }

    @Override
    public Result getLikeName(String name) {
        List<String> list=subjectMapper.getLikeName(name);
        return new Result(StateCode.SUCCESS,list);
    }

    @Override
    public void dle(String name) {
        subjectMapper.dle(name);
    }
}
