package com.example.majiang.valid.base;

import com.example.majiang.*;

import java.util.List;

public class HunYiSeHu extends BaseHuValid {

    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        int[] wan = hmd.getWan();
        int[] suo = hmd.getSuo();
        int[] tong = hmd.getTong();
        int[] zi = hmd.getZi();
        ShowEswnzfbx eswnzfbx = parseShow(show);
        boolean haveZi = (eswnzfbx.getZi() > 0) || sum(zi) > 0;
        boolean haveWan = sum(wan) > 0 || eswnzfbx.getWan() > 0;
        boolean haveSuo = sum(suo) > 0 || eswnzfbx.getSuo() > 0;
        boolean haveTong = sum(tong) > 0 || eswnzfbx.getTong() > 0;
        if (((haveWan && !haveSuo && !haveTong) ||
                (!haveWan && haveSuo && !haveTong) || (!haveWan && !haveSuo && haveTong)) && haveZi) {
            return Fan.HUN_YI_SE;
        }
        return null;
    }
}
