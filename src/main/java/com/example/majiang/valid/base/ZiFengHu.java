package com.example.majiang.valid.base;

import com.example.majiang.*;
import com.example.majiang.valid.Hu;
import com.example.majiang.valid.HuValid;

import java.util.List;

@Hu
public class ZiFengHu implements HuValid {
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        int ziFeng = gameInfo.getZiFeng();
        int[] zi = hmd.getZi();
        if (zi[ziFeng] == 3) {
            return Fan.ZI_FENG_HU;
        } else {
            return null;
        }
    }
}
