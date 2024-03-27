package com.example.pctol.server.mapper;

import com.example.pctol.common.constant.OperationType;
import com.example.pctol.pojo.DTO.SubSearchDTO;
import com.example.pctol.pojo.VO.SubInfoVo;
import com.example.pctol.pojo.entity.Subject;
import com.example.pctol.server.annotation.AutoFill;
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
    @Insert("insert into subject(id,name,create_time,update_time,launcher,audit_state)" +
            "values (#{id},#{name},#{createTime},#{updateTime},#{launcher},#{auditState})")
    void add(Subject subject);

    Integer getDataAct(SubSearchDTO subSearchDTO);

    List<SubInfoVo> getData(SubSearchDTO subSearchDTO, int start, int pageSize);
}
