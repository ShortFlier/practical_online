package com.example.pctol.server.mapper;

import com.example.pctol.pojo.entity.TopicExcel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hp
 * @date 2024/4/1
 */
@Mapper
public interface TopicExcelMapper {
    @Insert("insert into topic_excel(name,url,launcher,create_time,excel_id) values (#{name},#{url},#{launcher},#{create_time},#{excelId})")
    void insert(TopicExcel topicExcel);
}
