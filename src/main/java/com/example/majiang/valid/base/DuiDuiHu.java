package com.example.majiang.valid.base;

import com.example.majiang.*;
import com.example.majiang.valid.Hu;
import com.example.majiang.valid.HuValid;

import java.util.List;

@Hu
public class DuiDuiHu implements HuValid {

    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        int[] wan = hmd.getWan();
        int[] suo = hmd.getSuo();
        int[] tong = hmd.getTong();
        int[] zi = hmd.getZi();
        int dui = 0;
        boolean qt = false;
        for (int n : wan) {
            if (n == 3) {
                dui++;
            }
            if (n == 2) {
                if (qt) return null;
                else qt = true;
            }
        }
        for (int n : suo) {
            if (n == 3) {
                dui++;
            }
            if (n == 2) {
                if (qt) return null;
                else qt = true;
            }
        }
        for (int n : tong) {
            if (n == 3) {
                dui++;
            }
            if (n == 2) {
                if (qt) return null;
                else qt = true;
            }
        }
        for (int n : zi) {
            if (n == 3) {
                dui++;
            }
            if (n == 2) {
                if (qt) return null;
                else qt = true;
            }
        }
        /**
         * 除去顺子都是
         */
        ShowEswnzfbx eswnzfbx = parseShow(show);
        dui += eswnzfbx.getGroupNum() - (eswnzfbx.getSuoShunZi() + eswnzfbx.getWanShunZi() + eswnzfbx.getTongShunZi());

        return dui == 4 && qt ? Fan.DUI_DUI_HU : null;
    }
}
