package com.example.pctol.server.mapper;

import com.example.pctol.pojo.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author hp
 * @date 2024/3/24
 */
@Mapper
public interface StudentMapper {
    @Select("select stu_id,account,password,name,email,phone,create_time from student where account=#{account}")
    Student login(String account);
}
