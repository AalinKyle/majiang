package com.example.majiang.valid.base;

import com.example.majiang.*;

import java.util.ArrayList;
import java.util.List;

public class YiBeiKouHu extends BaseHuValid {
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        List<MajGroup> tmp = new ArrayList<>();
        tmp.addAll(show);
        tmp.addAll(list);
        List<MajGroup> shunzi = new ArrayList<>();
        for (MajGroup majGroup : tmp) {
            if (majGroup.getType() == MajGroup.SHUN_ZI) shunzi.add(majGroup);
        }
        if (shunzi.size() < 2) return null;

        for (int i = 0; i < shunzi.size(); i++) {
            MajGroup ii = shunzi.get(i);
            for (int j = i + 1; j < shunzi.size(); j++) {
                MajGroup jj = shunzi.get(j);
                if (ii.equals(jj)) {
                    return Fan.YI_BEI_KOU;
                }
            }
        }
        return null;
    }
}
