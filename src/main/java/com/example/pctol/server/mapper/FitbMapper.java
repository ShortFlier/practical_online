package com.example.pctol.server.mapper;

import com.example.pctol.common.constant.OperationType;
import com.example.pctol.pojo.DTO.PracticalDTO;
import com.example.pctol.pojo.DTO.TopicSearchInfoDTO;
import com.example.pctol.pojo.DTO.TopicUpdateDTO;
import com.example.pctol.pojo.VO.TopicVO;
import com.example.pctol.pojo.entity.Topic;
import com.example.pctol.server.annotation.AutoFill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 填空表操作
 *
 * @author hp
 * @date 2024/3/29
 */
@Mapper
public interface FitbMapper extends TopicPublic{
    @Update("update fill_in_the_blank set update_time=#{localDateTime},subject_id=#{newId} where subject_id=#{id}")
    void updateSubject(Integer id, LocalDateTime localDateTime, Integer newId);

    void insert(List list);

    int getNumber(TopicSearchInfoDTO topicSearchInfoDTO);

    List<TopicVO> getList(TopicSearchInfoDTO topicSearchInfoDTO);

    @AutoFill(OperationType.UPDATE)
    void update(TopicUpdateDTO topicUpdateDTO);

    //随机获取一个题目
    Topic getRandom(PracticalDTO practicalDTO);

    List<Topic> getByIds(List<Integer> ids);
}
