package com.example.pctol.server.mapper;

import com.example.pctol.pojo.DTO.StuTestHisDTO;
import com.example.pctol.pojo.VO.TestVO;
import com.example.pctol.pojo.VO.TopicTestVO;
import com.example.pctol.pojo.entity.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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


    List<TestVO> getHis(StuTestHisDTO stuTestHisDTO);

    @Select("SELECT id, paper_id, launcher, student_id, create_time, update_time, grade FROM test WHERE id = #{testId}")
    Test getById(Long testId);

    @Select("select submit_answer,grade from paper_topic where test_id=#{testId} and topic_id=#{topicId} and type=#{type}")
    TopicTestVO getSmtAsw(Long testId, Long topicId,int type);
}
