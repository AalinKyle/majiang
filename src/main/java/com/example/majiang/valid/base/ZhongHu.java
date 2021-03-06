package com.example.majiang.valid.base;

import com.example.majiang.*;
import com.example.majiang.valid.Hu;
import com.example.majiang.valid.HuValid;

import java.util.List;

@Hu
public class ZhongHu implements HuValid {
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        int[] zi = hmd.getZi();
        if (zi[6] == 3) {
            return Fan.YI_HU;
        } else {
            for (MajGroup majGroup : show) {
                int type = majGroup.getType();
                if (type == MajGroup.MING_KE || type == MajGroup.AN_GANG || type == MajGroup.MING_GANG) {
                    List<GroupMaj> majs = majGroup.getMajs();
                    Maj maj = majs.get(0);
                    if (maj.getType() == Maj.ZI && maj.getContent() == 6) {
                        return Fan.YI_HU;
                    }
                }
            }
        }
        return null;
    }
}
