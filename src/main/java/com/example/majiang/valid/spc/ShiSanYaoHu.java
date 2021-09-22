package com.example.majiang.valid.spc;

import com.example.majiang.*;
import com.example.majiang.valid.Hu;
import com.example.majiang.valid.HuValid;
import com.example.majiang.valid.NoValidPreHuValid;

import java.util.List;

@Hu(preClazz = NoValidPreHuValid.class)
public class ShiSanYaoHu implements HuValid {
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
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
