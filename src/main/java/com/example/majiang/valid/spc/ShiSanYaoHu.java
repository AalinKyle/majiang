package com.example.majiang.valid.spc;

import com.example.majiang.*;

import java.util.List;

public class ShiSanYaoHu extends SpecialHuValid {
    @Override
    public Fan valid0(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, GameInfo gameInfo) {
        int[] wan = hmd.getWan();
        int[] suo = hmd.getSuo();
        int[] tong = hmd.getTong();
        int[] zi = hmd.getZi();
        int sum = 0;
        if (wan[0] > 0) {
            sum += 1;
        }
        if (wan[8] > 0) {
            sum += 1;
        }
        if (suo[0] > 0) {
            sum += 1;
        }
        if (suo[8] > 0) {
            sum += 1;
        }
        if (tong[0] > 0) {
            sum += 1;
        }
        if (tong[8] > 0) {
            sum += 1;
        }
        for (int i = 0; i < zi.length; i++) {
            if (zi[i] > 0) {
                sum += 1;
            }
        }
        return sum >= 13 ? Fan.SHI_SNA_YAO : null;
    }
}
