package com.example.majiang.valid.base;

import com.example.majiang.Fan;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;

import java.util.List;

public class HunQuanDaiYaoJiuHu extends BaseHuValid {
    @Override
    public Fan valid(int[] wan, int[] tong, int[] suo, int[] zi, List<MajGroup> show, List<Maj> discard, List<MajGroup> hand) {
        for (MajGroup h:hand){
            if (!h.containYaoJiu()){
                return null;
            }
        }
        return Fan.HUN_QUAN_DAI_YAO_JIU;
    }
}
