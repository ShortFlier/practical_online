package com.example.pctol.server.mapper;

import com.example.pctol.common.constant.OperationType;
import com.example.pctol.pojo.VO.MemberVO;
import com.example.pctol.pojo.entity.Homework;
import com.example.pctol.pojo.entity.Members;
import com.example.pctol.pojo.entity.Team;
import com.example.pctol.server.annotation.AutoFill;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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

    @Select("select team_id,student.stu_id,name,members.create_time from members,student where members.stu_id=student.stu_id and team_id=#{teamID} limit #{start},#{pageSize}")
    List<MemberVO> memberGet(int teamID,int pageSize, int start);

    @Select("select id,name,description,th_id,invitation_code,create_time,update_time from team where invitation_code=#{code}")
    Team getByCode(String code);

    @Insert("insert into members values(#{teamId},#{stuId},#{createTime})")
    void mebInsert(Members members);

    @Select("select team_id,difficulty,subject_id,create_time,update_time,over_time,radios,muls,judg,fitb,vocal from homework where team_id=#{teamId}")
    Homework getHomeworkById(long teamId);

    @AutoFill(OperationType.UPDATE)
    void workUpdate(Homework homework);

    @AutoFill(OperationType.UPDATE)
    @Insert("INSERT INTO homework(team_id, difficulty, subject_id, create_time, update_time, over_time, radios, muls, judg, fitb, vocal) VALUES (#{teamId}, #{difficulty}, #{subjectId}, #{createTime}, #{updateTime}, #{overTime}, #{radios}, #{muls}, #{judg}, #{fitb}, #{vocal});")
    void workInsert(Homework homework);

    @Select("select id,name,description,th_id,invitation_code,team.create_time,update_time from team,members where team.id=members.team_id and stu_id=#{stuId}")
    List<Team> getsByStu(long stuId);

    @Delete("delete from members where team_id=#{teamId} and stu_id=#{stuId}")
    void dleMeber(long teamId, long stuId);
}
