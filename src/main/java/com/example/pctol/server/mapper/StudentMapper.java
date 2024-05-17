package com.example.pctol.server.mapper;

import com.example.pctol.common.constant.OperationType;
import com.example.pctol.pojo.DTO.StuSearchDTO;
import com.example.pctol.pojo.entity.Student;
import com.example.pctol.server.annotation.AutoFill;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author hp
 * @date 2024/3/24
 */
@Mapper
public interface StudentMapper {
    @Select("select stu_id,account,password,name,email,phone,create_time,update_time from student where account=#{account}")
    Student login(String account);

    int getAct(StuSearchDTO stuSearchDTO);

    List<Student> pageGet(StuSearchDTO stuSearchDTO, int start, int pageSize);

    @AutoFill(OperationType.INSERT)
    @Insert("insert into student(account,name,password,email,phone,create_time,update_time)" +
            "values (#{account},#{name},#{password},#{email},#{phone},#{createTime},#{updateTime})")
    void insert(Student student);

    @Select("select stu_id,account,name,email,phone,create_time,update_time from student where stu_id=#{id}")
    Student getById(long id);
}
