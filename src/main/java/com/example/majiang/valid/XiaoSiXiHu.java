package com.example.majiang.valid;

import com.example.majiang.Fan;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;
import com.example.majiang.ShowEswnzfbx;

import java.util.List;

public class XiaoSiXiHu extends BaseHuValid {
    @Override
    public Fan valid(int[] wan, int[] tong, int[] suo, int[] zi, List<MajGroup> show, List<Maj> discard) {
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
