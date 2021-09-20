package com.example.majiang.valid.base;

import com.example.majiang.*;

import java.util.List;

public class HunQuanDaiYaoJiuHu extends BaseHuValid {
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> hand, GameInfo gameInfo) {
        for (MajGroup h:hand){
            if (!h.containYaoJiu()){
                return null;
            }
        }
        return Fan.HUN_QUAN_DAI_YAO_JIU;
    }
}
