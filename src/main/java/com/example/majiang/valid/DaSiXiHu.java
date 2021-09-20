package com.example.majiang.valid;

import com.example.majiang.Fan;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;
import com.example.majiang.ShowEswnzfbx;

import java.util.List;

public class DaSiXiHu extends BaseHuValid {
    @Override
    public Fan valid(int[] wan, int[] tong, int[] suo, int[] zi, List<MajGroup> show, List<Maj> discard) {
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
