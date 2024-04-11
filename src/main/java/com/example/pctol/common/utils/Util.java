package com.example.pctol.common.utils;

import java.util.Random;

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
}
