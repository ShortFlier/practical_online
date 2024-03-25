package com.example.pctol.server.service.impl;

import com.example.pctol.common.constant.AuditState;
import com.example.pctol.common.properties.BaseContext;
import com.example.pctol.pojo.entity.Subject;
import com.example.pctol.server.mapper.SubjectMapper;
import com.example.pctol.server.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
