package com.example.majiang.valid.base;

import com.example.majiang.*;
import com.example.majiang.valid.Hu;
import com.example.majiang.valid.HuValid;

import java.util.ArrayList;
import java.util.List;

@Hu
public class YiBeiKouHu implements HuValid {
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        List<MajGroup> shunzi = new ArrayList<>();
        for (MajGroup majGroup : list) {
            if (majGroup.getType() == MajGroup.SHUN_ZI) {
                shunzi.add(majGroup);
            }
        }
        if (shunzi.size() < 2) {
            return null;
        }
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
