package com.example.majiang.valid.base;

import com.example.majiang.*;
import com.example.majiang.valid.Hu;
import com.example.majiang.valid.HuValid;

import java.util.List;

@Hu
public class HunLaoTouHu implements HuValid {
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        int[] wan = hmd.getWan();
        int[] suo = hmd.getSuo();
        int[] tong = hmd.getTong();
        for (int i = 1; i < wan.length - 1; i++) {
            if (wan[i] > 0 || tong[i] > 0 || suo[i] > 0) {
                return null;
            }
        }
        for (MajGroup majGroup : list) {
            List<GroupMaj> majs = majGroup.getMajs();
            for (Maj m : majs) {
                if (m.getType() != 3) {
                    if (m.getContent() != 0 && m.getContent() != 8) {
                        return null;
                    }
                }
            }
        }
        return Fan.HUN_LAO_TOU;
    }
}
