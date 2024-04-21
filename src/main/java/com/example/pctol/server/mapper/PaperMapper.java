package com.example.pctol.server.mapper;

import com.example.pctol.pojo.DTO.PaperSearchDTO;
import com.example.pctol.pojo.entity.Paper;
import com.example.pctol.pojo.entity.PaperDetail;
import com.example.pctol.pojo.entity.PaperTopic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author hp
 * @date 2024/4/15
 */
@Mapper
public interface PaperMapper {
    int count(PaperSearchDTO paperSearchDTO);

    List<Paper> gets(PaperSearchDTO paperSearchDTO,Integer start,Integer pageSize);

    @Select("select id,title,description,create_time,update_time,duration,launcher,subject_id,total_marks,difficulty,display" +
            " from paper where id=#{id}")
    Paper getPaper(long id);

    @Select("select paper_id,radio_marks,radio_ids,mul_marks,mul_ids,judg_marks,judg_ids,fitb_marks,fitb_ids,voc_marks,voc_ids,create_time,update_time" +
            " from paper_detail where paper_id=#{id}")
    PaperDetail getPaperDetail(long id);

    //保存到paper_topic
    void insertPaperTopic(List<PaperTopic> paperTopics);
}
