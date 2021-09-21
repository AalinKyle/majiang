package com.example.majiang.valid.base;

import com.example.majiang.*;

import java.util.List;

public class QingLaoTouHu extends BaseHuValid {
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        int[] wan = hmd.getWan();
        int[] suo = hmd.getSuo();
        int[] tong = hmd.getTong();
        int[] zi = hmd.getZi();
        for (int i = 1; i < wan.length - 1; i++) {
            if (wan[i] > 0 || tong[i] > 0 || suo[i] > 0 || zi[i] > 0) return null;
        }
        for (MajGroup majGroup : list) {
            List<Maj> majs = majGroup.getMajs();
            for (Maj m : majs) {
                if (m.getType() == Maj.ZI) return null;
                else {
                    if (m.getContent() != 0 && m.getContent() != 8) return null;
                }
            }
        }
        return Fan.QING_LAO_TOU;
    }
}
