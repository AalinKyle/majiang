package com.example.majiang.valid.base;

import com.example.majiang.*;

import java.util.List;

public class ZiYiSeHu extends BaseHuValid {
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        int[] wan = hmd.getWan();
        int[] suo = hmd.getSuo();
        int[] tong = hmd.getTong();
        ShowEswnzfbx eswnzfbx = parseShow(show);
        boolean haveZi = eswnzfbx.onlyHave(3);
        return haveZi && sum(wan) == 0 && sum(suo) == 0 && sum(tong) == 0 ? Fan.ZI_YI_SE : null;
    }
}
