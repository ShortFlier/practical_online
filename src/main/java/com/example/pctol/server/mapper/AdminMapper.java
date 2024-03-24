package com.example.pctol.server.mapper;

import com.example.pctol.pojo.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author hp
 * @date 2024/3/24
 */
@Mapper
public interface AdminMapper {
    @Select("select account,password,name,create_time from admin where account=#{account}")
    Admin login(String account);
}
