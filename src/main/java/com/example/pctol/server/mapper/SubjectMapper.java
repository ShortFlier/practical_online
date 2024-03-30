package com.example.pctol.server.mapper;

import com.example.pctol.common.constant.OperationType;
import com.example.pctol.pojo.DTO.SubSearchDTO;
import com.example.pctol.pojo.DTO.SubUpdateInfo;
import com.example.pctol.pojo.VO.SubInfoVo;
import com.example.pctol.pojo.entity.Subject;
import com.example.pctol.server.annotation.AutoFill;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author hp
 * @date 2024/3/25
 */
@Mapper
public interface SubjectMapper {
    @AutoFill(value = OperationType.INSERT)
    @Insert("insert into subject(name,create_time,update_time,launcher,audit_state,audit_time)" +
            "values (#{name},#{createTime},#{updateTime},#{launcher},#{auditState},#{auditTime})")
    void add(Subject subject);

    Integer getDataAct(SubSearchDTO subSearchDTO);

    List<SubInfoVo> getData(SubSearchDTO subSearchDTO, int start, int pageSize);


    List<String> getLikeName(String name);

    @Delete("delete from subject where name=#{name}")
    void dle(String name);

    @AutoFill(OperationType.UPDATE)
    void update(SubUpdateInfo subUpdateInfo);

    @Select("select id,name,create_time,update_time,launcher,audit_state,audit_time from subject where id=#{id}")
    Subject getById(Integer id);


    @Select("select id,name,create_time,update_time,launcher,audit_state,audit_time from subject where audit_state=#{auditState} limit #{start},#{pageSize}")
    List<Subject> getByAudit(Integer auditState, int start, Integer pageSize);

    @Select("select count(*) from subject where audit_state=#{auditState}")
    Integer getTotalByAudit(Integer auditState);
}
