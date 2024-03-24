package com.example.pctol.server.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.example.pctol.pojo.entity.Teacher;
import org.apache.ibatis.annotations.Select;

/**
 * @author hp
 * @date 2024/3/24
 */
@Mapper
public interface TeacherMapper {
    @Select("select th_id,account,password,name,email,phone,create_time from teacher where account=#{account}")
    Teacher login(String account);
}
