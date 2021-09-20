package com.example.majiang.valid.base;

import com.example.majiang.*;

import java.util.List;

public class PiHu extends BaseHuValid {

    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        return Fan.PI_HU;
    }


}
