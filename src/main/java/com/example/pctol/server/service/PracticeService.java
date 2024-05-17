package com.example.pctol.server.service;

import com.example.pctol.pojo.VO.GradeSechVO;
import com.example.pctol.pojo.VO.PracticeDateVO;
import com.example.pctol.pojo.entity.Practice;

import java.util.List;

/**
 * @author hp
 * @date 2024/4/12
 */
public interface PracticeService {
    void add(Practice practice);

    List all(GradeSechVO gradeSechVO);

    int[] rate(GradeSechVO gradeSechVO);

    PracticeDateVO date(GradeSechVO gradeSechVO);
}
