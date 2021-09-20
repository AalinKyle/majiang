package com.example.majiang.valid.base;

import com.example.majiang.*;

import java.util.List;

public class ChangFengHu extends BaseHuValid {
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        int CHANGFeng = gameInfo.getChangFeng();
        int[] zi = hmd.getZi();
        if (zi[CHANGFeng] == 3) {
            return Fan.CHANG_FENG_HU;
        } else return null;
    }
}
