package com.example.majiang.valid.base;

import com.example.majiang.*;

import java.util.List;

public class DuiDuiHu extends BaseHuValid {

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
        ShowEswnzfbx eswnzfbx = parseShow(show);
        if (eswnzfbx.getGroupNum() > 0) {
            dui += eswnzfbx.getWanMingKe();
            dui += eswnzfbx.getSuoMingKe();
            dui += eswnzfbx.getTongMingKe();
            dui += eswnzfbx.getFengMingKe();
            dui += eswnzfbx.getYiMingKe();
        }

        return dui == 4 && qt ? Fan.DUI_DUI_HU : null;
    }
}
