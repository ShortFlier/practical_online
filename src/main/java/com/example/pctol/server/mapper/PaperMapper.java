package com.example.pctol.server.mapper;

import com.example.pctol.pojo.DTO.PaperSearchDTO;
import com.example.pctol.pojo.entity.Paper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author hp
 * @date 2024/4/15
 */
@Mapper
public interface PaperMapper {
    int count(PaperSearchDTO paperSearchDTO);

    List<Paper> gets(PaperSearchDTO paperSearchDTO,Integer start,Integer pageSize);
}
