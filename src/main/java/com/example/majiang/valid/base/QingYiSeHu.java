package com.example.majiang.valid.base;

import com.example.majiang.*;

import java.util.List;

public class QingYiSeHu extends BaseHuValid {


    /**
     * 不判断全是字的，全是字是字一色
     *
     * @param wan
     * @param tong
     * @param suo
     * @param zi
     * @param show
     * @param discard
     * @return
     */
    @Override
    public Fan valid(int[] wan, int[] tong, int[] suo, int[] zi, List<MajGroup> show, List<Maj> discard, List<MajGroup> list) {
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
