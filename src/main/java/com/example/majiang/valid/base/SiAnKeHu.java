package com.example.majiang.valid.base;

import com.example.majiang.Fan;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;

import java.util.List;

public class SiAnKeHu extends BaseHuValid {
    @Override
    public Fan valid(int[] wan, int[] tong, int[] suo, int[] zi, List<MajGroup> show, List<Maj> discard,List<MajGroup> list) {
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
        return dui == 4 ? Fan.SI_AN_KE : null;
    }
}
