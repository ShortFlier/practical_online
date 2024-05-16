package com.example.pctol.server.service.impl;

import com.example.pctol.common.constant.TopicConstant;
import com.example.pctol.common.utils.Util;
import com.example.pctol.pojo.VO.*;
import com.example.pctol.pojo.entity.Homework;
import com.example.pctol.pojo.entity.Members;
import com.example.pctol.pojo.entity.Team;
import com.example.pctol.server.mapper.PracticeMapper;
import com.example.pctol.server.mapper.SubjectMapper;
import com.example.pctol.server.mapper.TeacherMapper;
import com.example.pctol.server.mapper.TeamMapper;
import com.example.pctol.server.service.PracticeService;
import com.example.pctol.server.service.TeamService;
import com.example.pctol.server.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author hp
 * @date 2024/5/15
 */
@Service
@Slf4j
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private PracticeMapper practiceMapper;

    @Override
    public Result thGets(long thId) {
        List<Team> teamList=teamMapper.thGets(thId);
        return Result.success(teamList);
    }

    @Override
    public long add(Team team) {
        //生成邀请码
        String code= UUID.randomUUID().toString();
        log.info("邀请码为：{}",code);
        team.setInvitationCode(code);
        //获取插入数据库的id
        teamMapper.insert(team);
        return team.getId();
    }

    @Override
    public void dle(Long id) {
        teamMapper.delete(id);
    }

    @Override
    public Team get(long id) {
        Team team=teamMapper.get(id);
        return team;
    }

    @Override
    public long totalMember(long id) {
        return teamMapper.totalMember(id);
    }

    @Override
    public List memberGet(int teamId, int page, int pageSize) {
        List<MemberVO> memberVOList=teamMapper.memberGet(teamId,pageSize,(page-1)*pageSize);
        return memberVOList;
    }

    @Override
    public Result join(String code, Long stuId) {
        //根据邀请码获取小组
        Team team=teamMapper.getByCode(code);
        if(team==null)
            return Result.error("邀请码有误");
        try{
            //写入成员表
            Members members=new Members(team.getId(), stuId, LocalDateTime.now());
            teamMapper.mebInsert(members);
        }catch (Exception e){
            return Result.error("不能重复加入");
        }
        return Result.success("加入成功");
    }

    @Override
    public Result workGet(long teamId) {
        //获取HomeWork对象全字段
        Homework homework=teamMapper.getHomeworkById(teamId);
        if(homework==null)
            return Result.error("没有布置作业");
        //获取SubjectName
        List<SubVO> subVOList=subjectMapper.getList();
        String name= Util.getSubjectName(homework.getSubjectId(), subVOList);
        WorkVO workVO=new WorkVO(homework);
        workVO.setSubjectName(name);
        return Result.success(workVO);
    }

    @Override
    public Result workSet(Homework homework) {
        if(!homework.good())
            return Result.error("请填写完整");
        try{ //尝试新增homework
            teamMapper.workInsert(homework);
            log.info("插入homework");
        }catch (Exception e){ //新增失败，向homework更新数据
            System.out.println(homework);
            teamMapper.workUpdate(homework);
            log.info("更新homework");
        }
        return Result.success("作业布置成功");
    }

    @Override
    public List<StuTeamVO> stuTeam(long stuId) {
        //获取全部加入的小组
        List<Team> teamList=teamMapper.getsByStu(stuId);
        List<StuTeamVO> stuTeamVOList=new ArrayList<>();
        for (int i = 0; i < teamList.size(); i++) {
            StuTeamVO stuTeamVO=new StuTeamVO();
            stuTeamVO.setId(teamList.get(i).getId());
            stuTeamVO.setName(teamList.get(i).getName());
            stuTeamVO.setTh_id(teamList.get(i).getThId());
            //获取thName
            String thName=teacherMapper.getById(teamList.get(i).getThId()).getName();
            stuTeamVO.setThName(thName);
            //作业完成率计算
            //获取作业信息
            Homework homework=teamMapper.getHomeworkById(teamList.get(i).getId());
            stuTeamVO.setFinish(workSts(homework,stuId));
            stuTeamVOList.add(stuTeamVO);
        }
        return stuTeamVOList;
    }

    private int[] workSts(Homework homework,long stuId){
        int[] stsArr=new int[10];
        for (int i = 0; i < 5; i++) {
            stsArr[i]= homework.getNumber(i);
            int j=i+5;
            if(stsArr[i]!=0) //获取该阶段学生答题数量
                stsArr[j]=practiceMapper.workNumber(homework,stuId,TopicConstant.TOPIC_TYPE_ARRAY[i]);
        }
        return stsArr;
    }
}
