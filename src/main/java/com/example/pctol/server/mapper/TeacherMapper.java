package com.example.pctol.server.mapper;

import com.example.pctol.pojo.DTO.ThSearchDTO;
import org.apache.ibatis.annotations.Mapper;
import com.example.pctol.pojo.entity.Teacher;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author hp
 * @date 2024/3/24
 */
@Mapper
public interface TeacherMapper {
    @Select("select th_id,account,password,name,email,phone,create_time from teacher where account=#{account}")
    Teacher login(String account);

    int getAct(ThSearchDTO thSearchDTO);

    List<Teacher> pageGet(ThSearchDTO thSearchDTO, int start, int pageSize);
}
