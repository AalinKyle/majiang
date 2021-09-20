package com.example.majiang.valid.base;

import com.example.majiang.*;

import java.util.List;

public class BaiHu extends BaseHuValid {
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        int[] zi = hmd.getZi();
        if (zi[4] == 4) {
            return Fan.YI_HU;
        } else return null;
    }
}
