package com.example.majiang.valid.base;

import com.example.majiang.Fan;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;
import com.example.majiang.ShowEswnzfbx;

import java.util.List;

public class HunYiSeHu extends BaseHuValid {

    @Override
    public Fan valid(int[] wan, int[] tong, int[] suo, int[] zi, List<MajGroup> show, List<Maj> discard,List<MajGroup> list) {
        ShowEswnzfbx eswnzfbx = parseShow(show);
        boolean haveZi = (eswnzfbx.getZi() > 0) || sum(zi) > 0;
        boolean haveWan = sum(wan) > 0 || eswnzfbx.getWan() > 0;
        boolean haveSuo = sum(suo) > 0 || eswnzfbx.getSuo() > 0;
        boolean haveTong = sum(tong) > 0 || eswnzfbx.getTong() > 0;
        if (((haveWan && !haveSuo && !haveTong) ||
                (!haveWan && haveSuo && !haveTong) || (!haveWan && !haveSuo && haveTong))&&haveZi) {
            return Fan.HUN_YI_SE;
        }
        return null;
    }
}
