package com.example.majiang.valid.base;

import com.example.majiang.*;

import java.util.List;

public class BaiHu extends BaseHuValid {
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        int[] zi = hmd.getZi();
        if (zi[4] == 3) {
            return Fan.YI_HU;
        } else {
            for (MajGroup majGroup : show) {
                int type = majGroup.getType();
                if (type == MajGroup.MING_KE || type == MajGroup.AN_GANG || type == MajGroup.MING_GANG) {
                    List<Maj> majs = majGroup.getMajs();
                    Maj maj = majs.get(0);
                    if (maj.getType() == Maj.ZI && maj.getContent() == 4) return Fan.YI_HU;
                }
            }
        }
        return null;
    }
}
