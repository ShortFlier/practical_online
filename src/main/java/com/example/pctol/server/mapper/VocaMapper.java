package com.example.pctol.server.mapper;

import com.example.pctol.pojo.DTO.TopicSearchInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 应用题表操作
 *
 * @author hp
 * @date 2024/3/29
 */
@Mapper
public interface VocaMapper {
    @Update("update vocabulary_qst set update_time=#{localDateTime},subject_id=#{newId} where subject_id=#{id}")
    void updateSubject(Integer id, LocalDateTime localDateTime, Integer newId);

    void insert(List list);

    int getNumber(TopicSearchInfo topicSearchInfo);
}
