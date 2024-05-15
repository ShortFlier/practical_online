package com.example.pctol.server.mapper;

import com.example.pctol.common.constant.OperationType;
import com.example.pctol.pojo.VO.MemberVO;
import com.example.pctol.pojo.entity.Team;
import com.example.pctol.server.annotation.AutoFill;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author hp
 * @date 2024/5/15
 */
@Mapper
public interface TeamMapper {
    @Select("select id,name,description,th_id,invitation_code,create_time,update_time from team where th_id=#{thId}")
    List<Team> thGets(long thId);

    @AutoFill(OperationType.INSERT)
    void insert(Team team);

    @Delete("delete from team where id=#{id}")
    void delete(Long id);

    @Select("select id,name,description,th_id,invitation_code,create_time,update_time from team where id=#{id}")
    Team get(long id);

    @Select("select count(*) from members where team_id=#{id}")
    long totalMember(long id);

    @Select("select team_id,student.stu_id,name,members.create_time from members,student where members.stu_id=student.stu_id and team_id=#{teamId} limit #{start},{pageSize}")
    List<MemberVO> memberGet(int teamID,int pageSize, int start);
}
