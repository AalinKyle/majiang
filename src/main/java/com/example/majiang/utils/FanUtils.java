package com.example.majiang.utils;

import com.example.majiang.Fan;
import com.example.majiang.HuMaj;

import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/24 14:17
 */
public class FanUtils {
    public static int sumFan(List<Fan> list) {
        int sum = 0;
        boolean isYiMan = false;
        for (Fan fan : list) {
            if (fan.isYiMan()) {
                if (!isYiMan) {
                    sum = 0;
                }
                isYiMan = true;
            }
            if ((isYiMan && fan.isYiMan()) || !isYiMan) {
                sum += fan.getNum();
            }
        }
        return sum;
    }


}
