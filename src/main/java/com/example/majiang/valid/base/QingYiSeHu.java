package com.example.majiang.valid.base;

import com.example.majiang.*;
import com.example.majiang.valid.Hu;
import com.example.majiang.valid.HuValid;

import java.util.ArrayList;
import java.util.List;

@Hu
public class QingYiSeHu implements HuValid {


    /**
     * 不判断全是字的，全是字是字一色
     *
     * @param show
     * @param discard
     * @return
     */
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        int[] zi = hmd.getZi();
        ShowEswnzfbx eswnzfbx = parseShow(show);
        boolean haveZi = false;
        if (eswnzfbx.getFengAnGang() > 0 || eswnzfbx.getFengMingGang() > 0 || eswnzfbx.getFengMingKe() > 0 || eswnzfbx.getYiAnGang() > 0 || eswnzfbx.getYiMingGang() > 0 || eswnzfbx.getYiMingKe() > 0) {
            haveZi = true;
        }
        int t = -1;
        if (!haveZi && sum(zi) == 0) {
            ArrayList<MajGroup> newArr = new ArrayList<>(list);
            newArr.addAll(show);
            for (MajGroup m : newArr) {
                GroupMaj gm = m.getMajs().get(0);
                if (t == -1) {
                    t = gm.getType();
                } else {
                    if (t != gm.getType()) {
                        return null;
                    }
                }
            }
            return Fan.QING_YI_SE;
        }
        return null;
    }
}
