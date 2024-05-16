package com.example.pctol.server.mapper;

import com.example.pctol.common.constant.OperationType;
import com.example.pctol.pojo.DTO.ThSearchDTO;
import com.example.pctol.server.annotation.AutoFill;
import org.apache.ibatis.annotations.Insert;
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
    @Select("select th_id,account,password,name,email,phone,create_time,update_time from teacher where account=#{account}")
    Teacher login(String account);

    int getAct(ThSearchDTO thSearchDTO);

    List<Teacher> pageGet(ThSearchDTO thSearchDTO, int start, int pageSize);

    @AutoFill(OperationType.INSERT)
    @Insert("insert into teacher(account,name,password,email,phone,create_time,update_time)" +
            "values (#{account},#{name},#{password},#{email},#{phone},#{createTime},#{updateTime})")
    void insert(Teacher teacher);

    @Select("select th_id,account,name,email,phone,create_time,update_time from teacher where th_id=#{id}")
    Teacher getById(long id);
}
