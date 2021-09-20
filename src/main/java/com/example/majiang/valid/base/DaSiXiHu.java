package com.example.majiang.valid.base;

import com.example.majiang.*;

import java.util.List;

public class DaSiXiHu extends BaseHuValid {
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        int[] zi = hmd.getZi();
        int res = 0;
        for (int i = 0; i < 4; i++) {
            if (zi[i] == 3) res++;
        }
        ShowEswnzfbx eswnzfbx = parseShow(show);
        if (eswnzfbx.getGroupNum() > 0) {
            res += eswnzfbx.getFengMingGang();
            res += eswnzfbx.getFengMingKe();
            res += eswnzfbx.getFengAnGang();
        }

        return res == 4 ? Fan.DA_SI_XI : null;
    }
}
