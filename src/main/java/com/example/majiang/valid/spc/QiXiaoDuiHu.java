package com.example.majiang.valid.spc;

import com.example.majiang.*;

import java.util.List;

public class QiXiaoDuiHu extends SpecialHuValid {


    @Override
    public Fan valid0(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, GameInfo gameInfo) {
        int[] wan = hmd.getWan();
        int[] suo = hmd.getSuo();
        int[] tong = hmd.getTong();
        int[] zi = hmd.getZi();
        int duiNum = 0;
        for (int n : wan) {
            if (n == 2) duiNum++;
        }
        for (int n : suo) {
            if (n == 2) duiNum++;
        }
        for (int n : tong) {
            if (n == 2) duiNum++;
        }
        for (int n : zi) {
            if (n == 2) duiNum++;
        }
        return duiNum == 7 ? Fan.QI_XIAO_DUI : null;
    }
}
