package com.example.pctol.server.mapper;

import com.example.pctol.pojo.DTO.PracticalDTO;
import com.example.pctol.pojo.entity.Topic;

/**
 * 对5个表的公共sql查询
 * @author hp
 * @date 2024/4/11
 */

public interface TopicPublic {
    Topic getRandom(PracticalDTO practicalDTO);
}
