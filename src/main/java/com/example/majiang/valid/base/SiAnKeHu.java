package com.example.majiang.valid.base;

import com.example.majiang.*;

import java.util.List;

public class SiAnKeHu extends BaseHuValid {
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        int[] wan = hmd.getWan();
        int[] suo = hmd.getSuo();
        int[] tong = hmd.getTong();
        int[] zi = hmd.getZi();
        int dui = 0;
        boolean qt = false;
        for (int n : wan) {
            if (n == 2) {
                if (qt) return null;
                else qt = true;
            }
            if (n == 3) {
                dui++;
            }
        }
        for (int n : suo) {
            if (n == 2) {
                if (qt) return null;
                else qt = true;
            }
            if (n == 3) {
                dui++;
            }
        }
        for (int n : tong) {
            if (n == 2) {
                if (qt) return null;
                else qt = true;
            }
            if (n == 3) {
                dui++;
            }
        }
        for (int n : zi) {
            if (n == 2) {
                if (qt) return null;
                else qt = true;
            }
            if (n == 3) {
                dui++;
            }
        }
        return dui == 4 && qt ? Fan.SI_AN_KE : null;
    }
}
