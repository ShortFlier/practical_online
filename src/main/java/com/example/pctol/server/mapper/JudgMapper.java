package com.example.pctol.server.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

/**
 * 判断表操作
 *
 * @author hp
 * @date 2024/3/29
 */
@Mapper
public interface JudgMapper {
    @Update("update judgment set update_time=#{localDateTime},subject_id=#{newId} where subject_id=#{id}")
    void updateSubject(Integer id, LocalDateTime localDateTime, Integer newId);
}
