package com.example.majiang.valid.spc;

import com.example.majiang.*;
import com.example.majiang.valid.Hu;
import com.example.majiang.valid.HuValid;
import com.example.majiang.valid.NoValidPreHuValid;

import java.util.List;

@Hu(preClazz = NoValidPreHuValid.class)
public class QiXiaoDuiHu implements HuValid {
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        int[] wan = hmd.getWan();
        int[] suo = hmd.getSuo();
        int[] tong = hmd.getTong();
        int[] zi = hmd.getZi();
        int duiNum = 0;
        for (int n : wan) {
            if (n == 2) {
                duiNum++;
            }
        }
        for (int n : suo) {
            if (n == 2) {
                duiNum++;
            }
        }
        for (int n : tong) {
            if (n == 2) {
                duiNum++;
            }
        }
        for (int n : zi) {
            if (n == 2) {
                duiNum++;
            }
        }
        return duiNum == 7 ? Fan.QI_XIAO_DUI : null;
    }
}
