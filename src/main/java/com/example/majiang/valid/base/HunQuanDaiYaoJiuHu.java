package com.example.majiang.valid.base;

import com.example.majiang.*;
import com.example.majiang.valid.Hu;
import com.example.majiang.valid.HuValid;

import java.util.List;

@Hu
public class HunQuanDaiYaoJiuHu implements HuValid {
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> hand, GameInfo gameInfo) {
        for (MajGroup h : hand) {
            if (!h.containYaoJiu()) {
                return null;
            }
        }
        for (MajGroup h : show) {
            if (!h.containYaoJiu()) {
                return null;
            }
        }
        return Fan.HUN_QUAN_DAI_YAO_JIU;
    }
}
