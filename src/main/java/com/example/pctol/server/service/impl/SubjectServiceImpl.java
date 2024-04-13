package com.example.pctol.server.service.impl;

import com.example.pctol.common.constant.AuditState;
import com.example.pctol.common.constant.Constant;
import com.example.pctol.common.constant.MsgConstant;
import com.example.pctol.common.constant.StateCode;
import com.example.pctol.common.properties.BaseContext;
import com.example.pctol.pojo.DTO.SubSearchDTO;
import com.example.pctol.pojo.DTO.SubUpdateInfoDTO;
import com.example.pctol.pojo.VO.PageResult;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.pojo.VO.SubInfoVo;
import com.example.pctol.pojo.VO.SubVO;
import com.example.pctol.pojo.entity.Subject;
import com.example.pctol.server.mapper.SubjectMapper;
import com.example.pctol.server.service.SubjectService;
import com.example.pctol.server.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hp
 * @date 2024/3/25
 */
@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private TopicService topicService;

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
        System.out.println(rows);
        for (SubInfoVo item: rows) {
            item.computeTopicNumber();
        }
        return new PageResult(total, rows);
    }

    @Override
    public void adminAdd(Subject subject) {
        subject.setLauncher(BaseContext.getLoginInfo());
        subject.setAuditState(AuditState.ACCESS);
        subject.setAuditTime(LocalDateTime.now());
        System.out.println("subject:"+subject);
        subjectMapper.add(subject);
    }

    @Override
    public Result getLikeName(String name) {
        List<String> list=subjectMapper.getLikeName(name);
        return new Result(StateCode.SUCCESS,list);
    }

    @Override
    public void dle(String name) throws Exception {
        SubInfoVo subInfoVo=selectNotNull(name);
//        删除学科，解除与题目的关联，并且将与之关联的题目设为待审核
        subjectMapper.dle(name);
        LocalDateTime localDateTime=LocalDateTime.now();
        topicService.dleSubject(subInfoVo.getId(),localDateTime,null);
    }

    @Override
    public void update(SubUpdateInfoDTO subUpdateInfoDTO) throws Exception {
//        String name=subjectMapper.getById(subUpdateInfoDTO.getId()).getName();
//        selectNotNull(name);
        Subject subject=subjectMapper.getById(subUpdateInfoDTO.getId());
        if(subject.getAuditState()==AuditState.ACCESS)
            throw new Exception(MsgConstant.FAILED);
        subjectMapper.update(subUpdateInfoDTO);
    }

    @Override
    public Result getByAudit(Integer auditState, Integer page, Integer pageSize) {
        List<Subject> list=subjectMapper.getByAudit(auditState,(page-1)*pageSize,pageSize);
        Integer total=subjectMapper.getTotalByAudit(auditState);
        PageResult pageResult=new PageResult(total,list);
        return Result.success(pageResult);
    }

    @Override
    public Result getList() {
        List<SubVO> list=subjectMapper.getList();
        return Result.success(list);
    }

    private SubInfoVo selectNotNull(String name) throws Exception {
        SubSearchDTO subSearchDTO=new SubSearchDTO(name,null);
        List<SubInfoVo> list=subjectMapper.getData(subSearchDTO, Constant.DEFAULT_PAGE_START,Constant.DEFAULT_PAGE_SIZE);
        System.out.println("subInfoVO-list:"+list);
        SubInfoVo subInfoVo=list.get(0);
        if(subInfoVo.computeTopicNumber().getTopicNumber()>Constant.LIMIT_TOPIC_NUMBER)
            throw new Exception(MsgConstant.SUBJECT_DELETE_NOT_ALLOWED);
        return subInfoVo;
    }
}
