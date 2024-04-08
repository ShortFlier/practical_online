package com.example.pctol.server.mapper;

import com.example.pctol.pojo.entity.TopicExcel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author hp
 * @date 2024/4/1
 */
@Mapper
public interface TopicExcelMapper {
    void insert(TopicExcel topicExcel);

    @Update("update topic_excel set error_msg=#{msg} where id=#{id}")
    void updateEor(Integer id, String msg);
}
