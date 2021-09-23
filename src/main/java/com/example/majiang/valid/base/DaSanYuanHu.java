package com.example.majiang.valid.base;

import com.example.majiang.*;
import com.example.majiang.valid.Hu;
import com.example.majiang.valid.HuValid;

import java.util.List;

@Hu
public class DaSanYuanHu implements HuValid {
    private static final int PENG = 3;

    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        int res = 0;
        int[] zi = hmd.getZi();
        if (zi[Maj.ZI_BAI] == PENG) {
            res++;
        }
        if (zi[Maj.ZI_FA] == PENG) {
            res++;
        }
        if (zi[Maj.ZI_ZHONG] == PENG) {
            res++;
        }
        ShowEswnzfbx eswnzfbx = parseShow(show);
        if (eswnzfbx.getGroupNum() > 0) {
            res += eswnzfbx.getYiAnGang();
            res += eswnzfbx.getYiMingKe();
            res += eswnzfbx.getYiMingGang();
        }
        return res == 3 ? Fan.DA_SAN_YUAN : null;
    }
}
