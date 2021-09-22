package com.example.majiang.valid.base;

import com.example.majiang.*;
import com.example.majiang.valid.Hu;
import com.example.majiang.valid.HuValid;

import java.util.List;
@Hu
public class ChangFengHu implements HuValid {
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        int CHANGFeng = gameInfo.getChangFeng();
        int[] zi = hmd.getZi();
        if (zi[CHANGFeng] == 3) {
            return Fan.CHANG_FENG_HU;
        } else return null;
    }
}
