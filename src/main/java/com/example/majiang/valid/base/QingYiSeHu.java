package com.example.majiang.valid.base;

import com.example.majiang.*;

import java.util.List;

public class QingYiSeHu extends BaseHuValid {


    /**
     * 不判断全是字的，全是字是字一色
     *
     * @param show
     * @param discard
     * @return
     */
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        int[] wan = hmd.getWan();
        int[] suo = hmd.getSuo();
        int[] tong = hmd.getTong();
        int[] zi = hmd.getZi();
        ShowEswnzfbx eswnzfbx = parseShow(show);
        boolean haveZi = false;
        if (eswnzfbx.getFengAnGang() > 0 || eswnzfbx.getFengMingGang() > 0 || eswnzfbx.getFengMingKe() > 0 || eswnzfbx.getYiAnGang() > 0 || eswnzfbx.getYiMingGang() > 0 || eswnzfbx.getYiMingKe() > 0) {
            haveZi = true;
        }
        if (!haveZi && sum(zi) == 0) {
            boolean haveWan = sum(wan) > 0;
            boolean haveSuo = sum(suo) > 0;
            boolean haveTong = sum(tong) > 0;

            if ((haveWan && !haveSuo && !haveTong) || (!haveWan && haveSuo && !haveTong) || (!haveWan && !haveSuo && haveTong)) {
                return Fan.QING_YI_SE;
            }
        }
        return null;
    }
}
