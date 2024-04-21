package com.example.pctol.server.mapper;

import com.example.pctol.pojo.entity.Test;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hp
 * @date 2024/4/21
 */
@Mapper
public interface TestMapper {

    //需要获取存入id
    void insert(Test test);
}
