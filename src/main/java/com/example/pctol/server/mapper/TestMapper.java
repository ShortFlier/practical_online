package com.example.pctol.server.mapper;

import com.example.pctol.pojo.entity.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author hp
 * @date 2024/4/21
 */
@Mapper
public interface TestMapper {

    //需要获取存入id
    void insert(Test test);

    @Select("select sum(grade) from paper_topic where test_id=#{testId}")
    Integer totalGrade(Long testId);

    @Update("update test set grade=#{grade} where id=#{id}")
    void updateGrade(Test test);
}
