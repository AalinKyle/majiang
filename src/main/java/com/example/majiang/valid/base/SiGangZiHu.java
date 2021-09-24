package com.example.majiang.valid.base;

import com.example.majiang.*;
import com.example.majiang.valid.Hu;
import com.example.majiang.valid.HuValid;

import java.util.List;

@Hu
public class SiGangZiHu implements HuValid {
    private static final int PENG = 3;

    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        ShowEswnzfbx eswnzfbx = parseShow(show);
        if (eswnzfbx.getGangNum() == 4) {
            return Fan.SI_GANG_ZI;
        }
        return null;
    }
}
