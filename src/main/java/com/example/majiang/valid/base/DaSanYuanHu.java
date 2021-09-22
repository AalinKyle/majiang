package com.example.majiang.valid.base;

import com.example.majiang.*;
import com.example.majiang.valid.Hu;
import com.example.majiang.valid.HuValid;

import java.util.List;
@Hu
public class DaSanYuanHu implements HuValid {
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        int res = 0;
        int[] zi = hmd.getZi();
        if (zi[4] == 3) res++;
        if (zi[5] == 3) res++;
        if (zi[6] == 3) res++;
        ShowEswnzfbx eswnzfbx = parseShow(show);
        if (eswnzfbx.getGroupNum() > 0) {
            res += eswnzfbx.getYiAnGang();
            res += eswnzfbx.getYiMingKe();
            res += eswnzfbx.getYiMingGang();
        }
        return res == 3 ? Fan.DA_SAN_YUAN : null;
    }
}
