package com.example.majiang.valid;

import com.example.majiang.Fan;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;
import com.example.majiang.ShowEswnzfbx;

import java.util.List;

public class ZiYiSeHu extends BaseHuValid {
    @Override
    public Fan valid(int[] wan, int[] tong, int[] suo, int[] zi, List<MajGroup> show, List<Maj> discard) {
        ShowEswnzfbx eswnzfbx = parseShow(show);
        boolean haveZi = eswnzfbx.onlyHave(3);
        return haveZi&&sum(wan)==0&&sum(suo)==0&&sum(tong)==0 ? Fan.ZI_YI_SE : null;
    }
}
