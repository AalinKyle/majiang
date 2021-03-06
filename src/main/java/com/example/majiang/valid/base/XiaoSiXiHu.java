package com.example.majiang.valid.base;

import com.example.majiang.*;
import com.example.majiang.valid.Hu;
import com.example.majiang.valid.HuValid;

import java.util.List;
@Hu
public class XiaoSiXiHu implements HuValid {
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        int[] zi = hmd.getZi();
        boolean ziQt = false;
        int ziDui = 0;
        ShowEswnzfbx eswnzfbx = parseShow(show);
        int fengMingKe = eswnzfbx.getFengMingKe();
        int fengAnGang = eswnzfbx.getFengAnGang();
        int fengMingGang = eswnzfbx.getFengMingGang();
        for (int i = 0; i < 4; i++) {
            if (zi[i] == 3) {
                ziDui++;
            } else if (zi[i] == 2) {
                if (ziQt) return null;
                else ziQt = true;
            }
        }
        return ziDui + fengMingKe + fengAnGang + fengMingGang == 3 && ziQt ? Fan.XIAO_SI_XI : null;
    }
}
