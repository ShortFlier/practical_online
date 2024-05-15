package com.example.pctol.server.service;

import com.example.pctol.pojo.VO.Result;
import com.example.pctol.pojo.entity.Team;

import java.util.List;

/**
 * @author hp
 * @date 2024/5/15
 */
public interface TeamService {
    Result thGets(long thId);

    long add(Team team);

    void dle(Long id);

    Team get(long id);

    long totalMember(long id);

    List memberGet(int teamID,int page, int pageSize);
}
