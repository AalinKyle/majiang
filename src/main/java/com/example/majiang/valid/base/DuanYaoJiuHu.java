package com.example.majiang.valid.base;

import com.example.majiang.*;

import java.util.List;

public class DuanYaoJiuHu extends BaseHuValid {
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        int[] wan = hmd.getWan();
        int[] suo = hmd.getSuo();
        int[] tong = hmd.getTong();
        int[] zi = hmd.getZi();
        if (wan[0] == 0 && wan[8] == 0 && tong[0] == 0 && tong[8] == 0 && suo[0] == 0 && suo[8] == 0 && allSame(zi, 0) && duanyaojiu(show)) {
            return Fan.DUAN_YAO_JIU;
        } else {
            return null;
        }
    }

    private boolean duanyaojiu(List<MajGroup> show) {
        for (MajGroup majGroup : show) {
            List<Maj> majs = majGroup.getMajs();
            for (Maj maj : majs) {
                if (maj.getType() == Maj.ZI || maj.getContent() == 0 || maj.getContent() == 8) {
                    return false;
                }
            }
        }
        return true;
    }
}
