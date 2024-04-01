package com.example.pctol.server.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author hp
 * @date 2024/3/29
 */
public interface TopicService {

    void dleSubject(Integer id, LocalDateTime localDateTime,Integer newId);

    void excelHandler(MultipartFile file, Integer type) throws Exception;
}
