package com.example.pctol.server.mapper;

import com.example.pctol.pojo.DTO.PracticalDTO;
import com.example.pctol.pojo.DTO.TopicSearchInfoDTO;
import com.example.pctol.pojo.VO.TopicVO;
import com.example.pctol.pojo.entity.Topic;

import java.util.List;

/**
 * 对5个表的公共sql查询
 * @author hp
 * @date 2024/4/11
 */

public interface TopicPublic {

    int getNumber(TopicSearchInfoDTO topicSearchInfoDTO);
    Topic getRandom(PracticalDTO practicalDTO);

    List<TopicVO> getList(TopicSearchInfoDTO topicSearchInfoDTO);

    List<Topic> getByIds(List ids);

    List<Integer> randomC(int difficulty, int count);
}
