package com.example.majiang.valid;

import com.example.majiang.Fan;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;
import com.example.majiang.ShowEswnzfbx;

import java.util.List;

public class DaSanYuanHu extends BaseHuValid {
    @Override
    public Fan valid(int[] wan, int[] tong, int[] suo, int[] zi, List<MajGroup> show, List<Maj> discard) {
        int res = 0;
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
