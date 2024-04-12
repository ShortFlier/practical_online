package com.example.pctol.server.mapper;

import com.example.pctol.common.constant.OperationType;
import com.example.pctol.pojo.entity.Practice;
import com.example.pctol.server.annotation.AutoFill;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hp
 * @date 2024/4/12
 */
@Mapper
public interface PracticeMapper {
    @AutoFill(OperationType.INSERT)
    @Insert("insert into practice(id,type,topic_id,submit_id,submit_answer,create_time,update_time,grade) " +
            "values(#{id},#{type},#{topicId},#{submitId},#{submitAnswer},#{createTime},#{updateTime},#{grade})")
    void add(Practice practice);
}
