package com.example.pctol.common.utils;

import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author hp
 * @date 2024/4/11
 */
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
}
