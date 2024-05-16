package com.example.pctol.server.controller;

import com.example.pctol.pojo.VO.Result;
import com.example.pctol.pojo.VO.StuTeamVO;
import com.example.pctol.pojo.entity.Homework;
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
    public Result memberGet(int teamId,int page, int pageSize){
        log.info("**********************************[/team/memberGet]************************************");
        log.info("teamId：{}，page：{}，pageSize：{}",teamId,page,pageSize);
        List list=teamService.memberGet(teamId,page,pageSize);
        return Result.success(list);
    }

    //加入学习小组
    @PostMapping("/join")
    public Result join(String code,Long stuId){
        log.info("**********************************[/team/join]************************************");
        log.info("code：{}，stuId：{}，",code,stuId);
        Result result=teamService.join(code,stuId);
        return result;
    }

    //作业
    @GetMapping("/homework/{teamId}")
    public Result workGet(@PathVariable long teamId){
        log.info("**********************************[/team/homework/{teamId}]************************************");
        log.info("teamId：{}，",teamId);
        Result result=teamService.workGet(teamId);
        return result;
    }

    //作业布置
    @PutMapping("/homework/set")
    public Result workSet(@RequestBody Homework homework){
        log.info("**********************************[/team/homework/set]************************************");
        log.info("作业布置信息：{}，",homework);
        Result result=teamService.workSet(homework);
        return result;
    }

    @GetMapping("/stuTeam")
    public Result stuTeam(long stuId){
        log.info("**********************************[/team/stuTeam]************************************");
        log.info("stuId：{}，",stuId);
        List<StuTeamVO> stuTeamVOList=teamService.stuTeam(stuId);
        return Result.success(stuTeamVOList);
    }
}
