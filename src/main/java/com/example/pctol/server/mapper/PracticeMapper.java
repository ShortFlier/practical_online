package com.example.pctol.server.mapper;

import com.example.pctol.common.constant.OperationType;
import com.example.pctol.pojo.VO.GradeSechVO;
import com.example.pctol.pojo.VO.TopicVO;
import com.example.pctol.pojo.entity.Homework;
import com.example.pctol.pojo.entity.Practice;
import com.example.pctol.server.annotation.AutoFill;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hp
 * @date 2024/4/12
 */
@Mapper
public interface PracticeMapper {
    @AutoFill(OperationType.INSERT)
    @Insert("insert into practice(id,type,topic_id,submit_id,submit_answer,create_time,update_time,grade,subject_id) " +
            "values(#{id},#{type},#{topicId},#{submitId},#{submitAnswer},#{createTime},#{updateTime},#{grade},#{subjectId})")
    void add(Practice practice);

    @Select("select count(*) from practice where type=#{type} and submit_id=#{stuId} and create_time>=#{homework.createTime} and create_time<=#{homework.overTime} and subject_id=#{homework.subjectId}")
    int workNumber(Homework homework, long stuId, int type);

    List<TopicVO> all(GradeSechVO gradeSechVO, int type);

    List<Practice> rate(GradeSechVO gradeSechVO, int type);

    List<LocalDate> dateGet(GradeSechVO gradeSechVO);
}
