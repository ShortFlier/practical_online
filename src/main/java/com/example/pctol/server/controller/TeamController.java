package com.example.pctol.server.controller;

import com.example.pctol.pojo.VO.Result;
import com.example.pctol.pojo.entity.Team;
import com.example.pctol.server.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hp
 * @date 2024/5/15
 */
@RestController
@RequestMapping("/team")
@Slf4j
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping("/thGets")
    public Result thGets(long thId){
        log.info("**********************************[/team/thGets]************************************");
        log.info("thId:{}",thId);
        Result result=teamService.thGets(thId);
        return result;
    }

    @PostMapping("/add")
    public Result add(@RequestBody Team team){
        log.info("**********************************[/team/add]************************************");
        log.info("新增team:{}",team);
        long id=teamService.add(team);
        return Result.success(id);
    }

    @DeleteMapping("/dle")
    public Result delete(Long id){
        log.info("**********************************[/team/delete]************************************");
        log.info("删除team，Id:{}",id);
        teamService.dle(id);
        return Result.success("删除成功！");
    }

    @GetMapping("/get")
    public Result get(long id,long teacher){
        log.info("**********************************[/team/get]************************************");
        log.info("id：{}，thId：{}",id,teacher);
        Team team=teamService.get(id);
        return Result.success(team);
    }

    @GetMapping("/totalMember")
    public Result totalMember(long id){
        log.info("**********************************[/team/totalMember]************************************");
        log.info("id：{}",id);
        long sum=teamService.totalMember(id);
        return Result.success(sum);
    }

    @GetMapping("/memberGet")
    public Result memberGet(int teamID,int page, int pageSize){
        log.info("**********************************[/team/memberGet]************************************");
        log.info("teamId：{}，page：{}，pageSize：{}",teamID,page,pageSize);
        List list=teamService.memberGet(teamID,page,pageSize);
        return Result.success(list);
    }
}
