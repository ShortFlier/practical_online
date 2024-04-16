package com.example.pctol.common.utils;

import com.example.pctol.pojo.VO.SubVO;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.List;

/**
 * @author hp
 * @date 2024/4/11
 */
@Slf4j
public class Util {
    private static Random random = new Random();
    public static int getRandomIntInRange(int bottom, int top) {
        return random.nextInt(top - bottom + 1) + bottom;
    }

    public static char getRandomCharInRange(char bottom, char top) {
        return (char) (random.nextInt(top - bottom+ 1) + bottom);
    }

    public static String formatMluChoices(String answer){
        // 将字符串转换为小写，去除重复字符，排序
        String sortedStr = answer.toLowerCase()
                .chars()
                .distinct()
                .sorted()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
        return sortedStr;
    }

    public static String getSubjectName(Long id,List<SubVO> sublist){
        for (SubVO subvo: sublist) {
            log.info("id：{}，subInfo：{}",id,subvo);
            if(id.equals(subvo.getId()))
                return subvo.getName();
        }
        return null;
    }
}
