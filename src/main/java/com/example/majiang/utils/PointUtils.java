package com.example.majiang.utils;

import com.example.majiang.Fan;
import com.example.majiang.HuMaj;

import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/24 14:46
 */
public class PointUtils {
    public static int calPoint(HuMaj huMaj, List<Fan> fans, boolean isDealer) {
        if (fans.size() > 0) {
            int fan = FanUtils.sumFan(fans);
            boolean yiMan = fans.get(0).isYiMan();
            if (yiMan || fan > 4) {
                return getPointByFans(fan, true, isDealer);
            } else {
                int fu = ru(FuUtils.calFu(huMaj, fans));
                double point = fu * Math.pow(2, 2 + fan);
                return (int) point * (isDealer ? 6 : 4);
            }
        } else {
            throw new NullPointerException();
        }

    }

    private static int ru(int fu) {
        if (fu % 10 == 0) {
            return fu;
        } else {
            return (fu / 10 + 1) * 10;
        }
    }


    public static int getPointByFans(int fanNum, boolean yiMan, boolean isDealer) {
        if (!yiMan && fanNum < 5) {
            throw new IllegalArgumentException();
        }
        int a = 0;
        if (yiMan) {
            a = 8000;
        } else {
            switch (fanNum) {
                case 5: {
                    a = 2000;
                    break;
                }
                case 6:
                case 7: {
                    a = 3000;
                    break;
                }
                case 8:
                case 9:
                case 10: {
                    a = 4000;
                    break;
                }
                case 11:
                case 12: {
                    a = 6000;
                    break;
                }
                default: {
                    a = 8000;
                }
            }
        }
        return a * (isDealer ? 6 : 4);

    }
}
