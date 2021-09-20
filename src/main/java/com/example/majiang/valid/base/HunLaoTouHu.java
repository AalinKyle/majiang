package com.example.majiang.valid.base;

import com.example.majiang.Fan;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;
import com.example.majiang.ShowEswnzfbx;

import java.util.List;

public class HunLaoTouHu extends BaseHuValid {
    @Override
    public Fan valid(int[] wan, int[] tong, int[] suo, int[] zi, List<MajGroup> show, List<Maj> discard, List<MajGroup> list) {

        for (int i = 1; i < wan.length - 1; i++) {
            if (wan[i] > 0 || tong[i] > 0 || suo[i] > 0) return null;
        }
        for (MajGroup majGroup : list) {
            List<Maj> majs = majGroup.getMajs();
            for (Maj m : majs) {
                if (m.getType() != 3) {
                    if (m.getContent() != 0 && m.getContent() != 8) return null;
                }
            }
        }
        return Fan.HUN_LAO_TOU;
    }
}
