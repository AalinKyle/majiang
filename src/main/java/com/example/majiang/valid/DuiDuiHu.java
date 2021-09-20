package com.example.majiang.valid;

import com.example.majiang.Fan;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;
import com.example.majiang.ShowEswnzfbx;

import java.util.List;

public class DuiDuiHu extends BaseHuValid {

    @Override
    public Fan valid(int[] wan, int[] tong, int[] suo, int[] zi, List<MajGroup> show, List<Maj> discard) {
        int dui = 0;
        for (int n : wan) {
            if (n == 3) {
                dui++;
            }
        }
        for (int n : suo) {
            if (n == 3) {
                dui++;
            }
        }
        for (int n : tong) {
            if (n == 3) {
                dui++;
            }
        }
        for (int n : zi) {
            if (n == 3) {
                dui++;
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

        return dui == 4 ? Fan.DUI_DUI_HU : null;
    }
}
