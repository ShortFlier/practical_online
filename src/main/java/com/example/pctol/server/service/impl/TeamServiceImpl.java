package com.example.pctol.server.service.impl;

import com.example.pctol.pojo.VO.MemberVO;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.pojo.entity.Team;
import com.example.pctol.server.mapper.TeamMapper;
import com.example.pctol.server.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
